package com.framework.demo.annotation;

import java.lang.annotation.*;

/**
 * 交易处理器描述
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TransDescription {
    /**
     * 交易处理列表
     */
    public String[] transProcessorList() default {};
}
