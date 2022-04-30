package com.framework.demo.base;


/**
 * 处理器接口
 */

public interface ITransProcessor {

    public void process(TransContext transContext) throws Throwable;
    public void processHandle(TransContext transContext);
    public void setTransContext(TransContext transContext);
//    public TransContext getTransContext(TransContext transContext);
//    public ITransProcessor setNextProcessor(ITransProcessor processor);
    public void setStartTransProcessor(ITransProcessor startProcessor);

}
