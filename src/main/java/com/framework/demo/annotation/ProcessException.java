package com.framework.demo.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProcessException {
    public String exceptionProcessor() default "NoneExceptionProcessor";
}
