package com.icezhg.sunflower.aspect;


import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.domain.OperationLog;
import com.icezhg.sunflower.service.OperationLogService;
import com.icezhg.sunflower.util.JsonUtil;
import com.icezhg.sunflower.util.Requests;
import com.icezhg.sunflower.util.SecurityUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2022/12/23.
 */
@Aspect
@Component
public class OperationLogAspect {

    private final ThreadLocal<Long> timeLocal = new ThreadLocal<>();

    private final OperationLogService operationLogService;

    public OperationLogAspect(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @Before("@annotation(operation)")
    public void beforeOperation(Operation operation) {
        timeLocal.set(System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "@annotation(operation)", returning = "result")
    public void afterOperationReturning(JoinPoint jp, Operation operation, Object result) {
        createOperationLog(jp, operation, result, null);
    }

    @AfterThrowing(pointcut = "@annotation(operation)", throwing = "ex")
    public void afterOperationThrowing(JoinPoint jp, Operation operation, Exception ex) {
        createOperationLog(jp, operation, null, ex);
    }

    private void createOperationLog(JoinPoint jp, Operation operation, Object result, Exception ex) {
        long finishTime = System.currentTimeMillis();
        OperationLog operationLog = new OperationLog();
        operationLog.setCost(finishTime - timeLocal.get());
        operationLog.setCreateTime(new Date(finishTime));
        operationLog.setUserId(SecurityUtil.currentUserId());
        operationLog.setTitle(operation.title());
        operationLog.setOperationType(operation.type() != null ? operation.type().getValue() : "");
        operationLog.setMethod(jp.getSignature().toString());
        if (operation.saveParameter()) {
            operationLog.setParameter(StringUtils.substring(parameterString(jp.getArgs()), 0, 2000));
        }
        if (operation.saveResult() && result != null) {
            operationLog.setResult(StringUtils.substring(JsonUtil.toJson(result), 0, 2000));
        }
        if (ex == null) {
            operationLog.setStatus(Constant.NORMAL);
        } else {
            operationLog.setStatus(Constant.EXCEPTION);
            operationLog.setErrorMsg(StringUtils.substring(ExceptionUtils.getStackTrace(ex), 0, 2000));
        }

        HttpServletRequest request = Requests.getRequest();
        if (request != null) {
            operationLog.setRequestMethod(request.getMethod());
            operationLog.setRequestUrl(request.getRequestURI());
        }

        Map<String, String> attributes = SecurityUtil.currentUser().getAttributes();
        operationLog.setRequestIp(attributes.get(Constant.ATTRIBUTE_IP));
        operationLog.setRequestLocation(attributes.get(Constant.ATTRIBUTE_IP_LOCATION));

        operationLogService.addLog(operationLog);
    }

    private String parameterString(Object[] args) {
        List<String> argStrings = new ArrayList<>(args.length);
        for (Object arg : args) {
            if (arg instanceof ServletRequest || arg instanceof ServletResponse) {
                continue;
            }
            if (arg instanceof MultipartFile file) {
                List<String> attributes = new ArrayList<>();
                attributes.add(FilenameUtils.getName(FilenameUtils.normalize(file.getOriginalFilename())));
                attributes.add(file.getContentType());
                argStrings.add(StringUtils.join(attributes, ", "));
            } else if (isSimpleType(arg)) {
                argStrings.add(String.valueOf(arg));
            } else {
                argStrings.add(JsonUtil.toJson(arg));
            }
        }
        return StringUtils.join(argStrings, ", ");
    }

    private boolean isSimpleType(Object obj) {
        return obj instanceof CharSequence || obj instanceof Number || obj instanceof Boolean || obj instanceof Character;
    }

}
