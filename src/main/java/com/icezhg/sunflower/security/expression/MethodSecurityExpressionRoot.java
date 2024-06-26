package com.icezhg.sunflower.security.expression;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

/**
 * Created by zhongjibing on 2023/06/27.
 */
public class MethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;

    private Object returnObject;

    private Object target;

   public  MethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public MethodSecurityExpressionRoot(Supplier<Authentication> authentication) {
        super(authentication);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    public void setThis(Object target) {
        this.target = target;
    }

    @Override
    public Object getThis() {
        return this.target;
    }
}
