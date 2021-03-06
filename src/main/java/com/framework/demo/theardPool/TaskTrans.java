package com.framework.demo.theardPool;

import com.framework.demo.base.BaseTrans;
import com.framework.demo.base.TransContext;
import com.framework.demo.base.TransTemplate;

import java.util.concurrent.Callable;

public class TaskTrans implements Callable<Boolean> {
    private BaseTrans transName;
    private TransContext transContext;

    /**
     * @param transName
     * @param transContext
     */
    public TaskTrans(BaseTrans transName, TransContext transContext) {
        this.transName = transName;//交易任务
        //交易参数
        this.transContext = transContext;
    }

    @Override
    public Boolean call() throws Exception {
        TransTemplate.getInstance().tansExecute(transName, transContext);
        return true;
    }

}
