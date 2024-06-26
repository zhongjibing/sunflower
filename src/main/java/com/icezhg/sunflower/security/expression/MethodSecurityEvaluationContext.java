package com.icezhg.sunflower.security.expression;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.DefaultSecurityParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * Created by zhongjibing on 2023/06/27.
 */
public class MethodSecurityEvaluationContext extends MethodBasedEvaluationContext {

    public MethodSecurityEvaluationContext(Authentication user, MethodInvocation mi) {
        this(user, mi, new DefaultSecurityParameterNameDiscoverer());
    }

    public MethodSecurityEvaluationContext(Authentication user, MethodInvocation mi,
            ParameterNameDiscoverer parameterNameDiscoverer) {
        super(mi.getThis(), getSpecificMethod(mi), mi.getArguments(), parameterNameDiscoverer);
    }

    public MethodSecurityEvaluationContext(MethodSecurityExpressionOperations root, MethodInvocation mi,
            ParameterNameDiscoverer parameterNameDiscoverer) {
        super(root, getSpecificMethod(mi), mi.getArguments(), parameterNameDiscoverer);
    }

    private static Method getSpecificMethod(MethodInvocation mi) {
        return AopUtils.getMostSpecificMethod(mi.getMethod(), AopProxyUtils.ultimateTargetClass(mi.getThis()));
    }
}
