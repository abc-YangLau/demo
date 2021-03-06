package com.framework.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // 注解声明一个切面
@Component // 受spring管理的容器
public class RetryAspect {

    @Autowired
    private RetryScheduledEngine RetryScheduledEngine;
    // 注解声明切点
    @Pointcut("@annotation(com.framework.aop.Retry)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public Object before(ProceedingJoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Retry[] retryAnnotations = method.getAnnotationsByType(Retry.class);
        Retry retryAnnotation = retryAnnotations[0];

        long delayed = retryAnnotation.delayed();
        long interval = retryAnnotation.interval();
        int retryTimes = retryAnnotation.retryTimes();

        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();

        // 构建参数
        RetryInvorkParam param = new RetryInvorkParam();
        param.setArgs(args);
        param.setCurrentTimes(0);
        param.setDelayed(delayed);
        param.setEnd(false);
        param.setInterval(interval);
        param.setInvorkMethod(method);
        param.setRetryTimes(retryTimes);
        param.setTarget(target);

        RetryScheduledEngine.submitTask(param);

        return false;
    }
}
