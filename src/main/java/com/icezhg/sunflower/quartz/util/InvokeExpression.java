package com.icezhg.sunflower.quartz.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvokeExpression {

    private static final Pattern PATTERN = Pattern.compile("^((\\w+\\.)*(\\w+))\\.(\\w+)(\\((.*)\\))?$");

    private final String invokeExpression;

    private transient String executableName;
    private transient String methodName;
    private transient String parametersString;
    private transient List<InvokeParameter> parameters;
    private transient boolean validExpression;

    private InvokeExpression(String invokeExpression) {
        if (invokeExpression == null) {
            throw new IllegalArgumentException("invokeExpression cannot be null");
        }

        this.invokeExpression = invokeExpression.trim();
        buildExpression();
    }

    public static InvokeExpression instanceOf(String invokeExpression) {
        return new InvokeExpression(invokeExpression);
    }

    public boolean isValid() {
        return validExpression;
    }

    public String getExecutableName() {
        return executableName;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<InvokeParameter> getParameters() {
        return parameters != null ? parameters : List.of();
    }

    private void buildExpression() {
        Matcher matcher = PATTERN.matcher(this.invokeExpression);
        if (matcher.find()) {
            this.executableName = matcher.group(1);
            this.methodName = matcher.group(4);
            this.parametersString = matcher.group(6);

            this.validExpression = true;

            if (this.parametersString != null && !this.parametersString.isBlank()) {
                this.validExpression = buildParameter();
            }
        } else {
            this.validExpression = false;
        }
    }

    private boolean buildParameter() {
        List<String> paramItems;
        try {
            paramItems = splitParameter();
        } catch (ParseException e) {
            return false;
        }

        this.parameters = paramItems.stream().map(item -> {
            if (StringUtils.startsWith(item, "'") && StringUtils.endsWith(item, "'")) {
                return new InvokeParameter(String.class, StringUtils.substring(item, 1, item.length() - 1));
            } else if (StringUtils.startsWith(item, "\"") && StringUtils.endsWith(item, "\"")) {
                return new InvokeParameter(String.class, StringUtils.substring(item, 1, item.length() - 1));
            } else if (StringUtils.equalsAnyIgnoreCase(item, "true", "false")) {
                return new InvokeParameter(boolean.class, Boolean.parseBoolean(item));
            } else if (NumberUtils.isCreatable(item)) {
                Number number = NumberUtils.createNumber(item);
                return new InvokeParameter(number.getClass(), number);
            } else {
                return new InvokeParameter(String.class, item);
            }
        }).toList();

        return true;
    }

    private List<String> splitParameter() throws ParseException {
        String param = this.parametersString.trim();
        Stack<Character> stack = new Stack<>();
        List<String> paramItems = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean escape = false;
        boolean expectFlag = true;
        for (int i = 0; i < param.length(); i++) {
            char chr = param.charAt(i);
            if (escape) {
                builder.append(chr);
                escape = false;
            } else if (chr == '\'') {
                expectFlag = false;
                if (stack.isEmpty()) {
                    stack.push(chr);
                } else {
                    if (stack.peek() == '\'') {
                        stack.pop();
                        paramItems.add('\'' + popBuilderValue(builder, false) + '\'');
                    } else {
                        builder.append(chr);
                    }
                }
            } else if (chr == '"') {
                expectFlag = false;
                if (stack.isEmpty()) {
                    stack.push(chr);
                } else {
                    if (stack.peek() == '"') {
                        stack.pop();
                        paramItems.add('"' + popBuilderValue(builder, false) + '"');
                    } else {
                        builder.append(chr);
                    }
                }
            } else if (chr == ' ') {
                if (!stack.isEmpty()) {
                    builder.append(chr);
                }
            } else if (chr == ',') {
                if (stack.isEmpty()) {
                    if (!builder.isEmpty()) {
                        paramItems.add(popBuilderValue(builder, true));
                        expectFlag = true;
                    } else {
                        if (expectFlag) {
                            throw new ParseException("parameter error", i);
                        }
                    }
                } else {
                    builder.append(chr);
                }
            } else if (chr == '\\') {
                escape = true;
            } else {
                expectFlag = false;
                builder.append(chr);
            }
        }

        if (!stack.isEmpty()) {
            throw new ParseException("bad invokeTarget", -1);
        }

        if (!builder.isEmpty()) {
            paramItems.add(popBuilderValue(builder, true));
        }

        return paramItems;
    }

    private String popBuilderValue(StringBuilder builder, boolean trim) {
        String result = builder.toString();
        if (trim) {
            result = result.trim();
        }
        builder.delete(0, builder.length());
        return result;
    }
}
