package com.framework.aop.service;

import com.framework.aop.Retry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AopTimeTask")
public class AopTimeTask {
    /** 测试方法 */
    @RequestMapping("/task")
    @Retry(delayed = 5, interval = 2, retryTimes = 10)
    public boolean testRetryInvoke() {
        System.out.println("______________testRetryInvoke被执行_________________");
        return false;
    }
}
