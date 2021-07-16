package com.framework.demo.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ITransProcessorProxy implements InvocationHandler {

    private Object proxied;
    public ITransProcessorProxy(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return  method.invoke(proxied,args);
    }
}
