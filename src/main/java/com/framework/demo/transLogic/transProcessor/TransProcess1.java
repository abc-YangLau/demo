package com.framework.demo.transLogic.transProcessor;

import com.framework.aop.RetryRunning;
import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.management.LockInfo;
import java.math.BigDecimal;

@Component
public class TransProcess1 extends BaseTrans {
    private static final Logger logger = LoggerFactory.getLogger(BaseTrans.class);
    @Override
    public void processHandle(TransContext transContext) {
        logger.info("{}:第1个业务处理逻辑！{}",Thread.currentThread().getName(),transContext.getAmtTr());
        transContext.setAmtTr(transContext.getAmtTr().add(new BigDecimal(20)));
    }
}
