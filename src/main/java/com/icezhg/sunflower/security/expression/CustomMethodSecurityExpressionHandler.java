package com.icezhg.sunflower.security.expression;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.log.LogMessage;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.PermissionCacheOptimizer;
import org.springframework.security.access.expression.AbstractSecurityExpressionHandler;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.DefaultSecurityParameterNameDiscoverer;
import org.springframework.util.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by zhongjibing on 2023/06/27.
 */
public class CustomMethodSecurityExpressionHandler extends AbstractSecurityExpressionHandler<MethodInvocation>
        implements MethodSecurityExpressionHandler {
    protected final Log logger = LogFactory.getLog(getClass());

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultSecurityParameterNameDiscoverer();

    private PermissionCacheOptimizer permissionCacheOptimizer = null;

    private String defaultRolePrefix = "ROLE_";

    public CustomMethodSecurityExpressionHandler() {
    }


    @Override
    public StandardEvaluationContext createEvaluationContextInternal(Authentication auth, MethodInvocation mi) {
        return new MethodSecurityEvaluationContext(auth, mi, getParameterNameDiscoverer());
    }

    @Override
    public EvaluationContext createEvaluationContext(Supplier<Authentication> authentication, MethodInvocation mi) {
        MethodSecurityExpressionOperations root = createSecurityExpressionRoot(authentication, mi);
        MethodSecurityEvaluationContext ctx = new MethodSecurityEvaluationContext(root, mi,
                getParameterNameDiscoverer());
        ctx.setBeanResolver(getBeanResolver());
        return ctx;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
            MethodInvocation invocation) {
        return createSecurityExpressionRoot(() -> authentication, invocation);
    }

    private MethodSecurityExpressionOperations createSecurityExpressionRoot(Supplier<Authentication> authentication,
            MethodInvocation invocation) {
        MethodSecurityExpressionRoot root = new MethodSecurityExpressionRoot(authentication);
        root.setThis(invocation.getThis());
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(getTrustResolver());
        root.setRoleHierarchy(getRoleHierarchy());
        root.setDefaultRolePrefix(getDefaultRolePrefix());
        return root;
    }

    @Override
    public Object filter(Object filterTarget, Expression filterExpression, EvaluationContext ctx) {
        MethodSecurityExpressionOperations rootObject = (MethodSecurityExpressionOperations) ctx.getRootObject()
                .getValue();
        this.logger.debug(LogMessage.format("Filtering with expression: %s", filterExpression.getExpressionString()));
        if (filterTarget instanceof Collection) {
            return filterCollection((Collection<?>) filterTarget, filterExpression, ctx, rootObject);
        }
        if (filterTarget.getClass().isArray()) {
            return filterArray((Object[]) filterTarget, filterExpression, ctx, rootObject);
        }
        if (filterTarget instanceof Map) {
            return filterMap((Map<?, ?>) filterTarget, filterExpression, ctx, rootObject);
        }
        if (filterTarget instanceof Stream) {
            return filterStream((Stream<?>) filterTarget, filterExpression, ctx, rootObject);
        }
        throw new IllegalArgumentException(
                "Filter target must be a collection, array, map or stream type, but was " + filterTarget);
    }

    private <T> Object filterCollection(Collection<T> filterTarget, Expression filterExpression, EvaluationContext ctx,
            MethodSecurityExpressionOperations rootObject) {
        this.logger.debug(LogMessage.format("Filtering collection with %s elements", filterTarget.size()));
        List<T> retain = new ArrayList<>(filterTarget.size());
        if (this.permissionCacheOptimizer != null) {
            this.permissionCacheOptimizer.cachePermissionsFor(rootObject.getAuthentication(), filterTarget);
        }
        for (T filterObject : filterTarget) {
            rootObject.setFilterObject(filterObject);
            if (ExpressionUtils.evaluateAsBoolean(filterExpression, ctx)) {
                retain.add(filterObject);
            }
        }
        this.logger.debug(LogMessage.format("Retaining elements: %s", retain));
        filterTarget.clear();
        filterTarget.addAll(retain);
        return filterTarget;
    }

    private Object filterArray(Object[] filterTarget, Expression filterExpression, EvaluationContext ctx,
            MethodSecurityExpressionOperations rootObject) {
        List<Object> retain = new ArrayList<>(filterTarget.length);
        this.logger.debug(LogMessage.format("Filtering array with %s elements", filterTarget.length));
        if (this.permissionCacheOptimizer != null) {
            this.permissionCacheOptimizer.cachePermissionsFor(rootObject.getAuthentication(),
                    Arrays.asList(filterTarget));
        }
        for (Object filterObject : filterTarget) {
            rootObject.setFilterObject(filterObject);
            if (ExpressionUtils.evaluateAsBoolean(filterExpression, ctx)) {
                retain.add(filterObject);
            }
        }
        this.logger.debug(LogMessage.format("Retaining elements: %s", retain));
        Object[] filtered = (Object[]) Array.newInstance(filterTarget.getClass().getComponentType(), retain.size());
        for (int i = 0; i < retain.size(); i++) {
            filtered[i] = retain.get(i);
        }
        return filtered;
    }

    private <K, V> Object filterMap(final Map<K, V> filterTarget, Expression filterExpression, EvaluationContext ctx,
            MethodSecurityExpressionOperations rootObject) {
        Map<K, V> retain = new LinkedHashMap<>(filterTarget.size());
        this.logger.debug(LogMessage.format("Filtering map with %s elements", filterTarget.size()));
        for (Map.Entry<K, V> filterObject : filterTarget.entrySet()) {
            rootObject.setFilterObject(filterObject);
            if (ExpressionUtils.evaluateAsBoolean(filterExpression, ctx)) {
                retain.put(filterObject.getKey(), filterObject.getValue());
            }
        }
        this.logger.debug(LogMessage.format("Retaining elements: %s", retain));
        filterTarget.clear();
        filterTarget.putAll(retain);
        return filterTarget;
    }

    private Object filterStream(final Stream<?> filterTarget, Expression filterExpression, EvaluationContext ctx,
            MethodSecurityExpressionOperations rootObject) {
        return filterTarget.filter((filterObject) -> {
            rootObject.setFilterObject(filterObject);
            return ExpressionUtils.evaluateAsBoolean(filterExpression, ctx);
        }).onClose(filterTarget::close);
    }


    public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
        Assert.notNull(trustResolver, "trustResolver cannot be null");
        this.trustResolver = trustResolver;
    }


    protected AuthenticationTrustResolver getTrustResolver() {
        return this.trustResolver;
    }

    public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }

    protected ParameterNameDiscoverer getParameterNameDiscoverer() {
        return this.parameterNameDiscoverer;
    }

    public void setPermissionCacheOptimizer(PermissionCacheOptimizer permissionCacheOptimizer) {
        this.permissionCacheOptimizer = permissionCacheOptimizer;
    }

    @Override
    public void setReturnObject(Object returnObject, EvaluationContext ctx) {
        ((MethodSecurityExpressionOperations) ctx.getRootObject().getValue()).setReturnObject(returnObject);
    }

    public void setDefaultRolePrefix(String defaultRolePrefix) {
        this.defaultRolePrefix = defaultRolePrefix;
    }

    protected String getDefaultRolePrefix() {
        return this.defaultRolePrefix;
    }
}
