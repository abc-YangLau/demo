package com.framework.demo.base;


import com.framework.demo.annotation.ProcessException;
import com.framework.demo.util.BeanUtils;

import java.lang.reflect.Proxy;

public abstract class BaseTrans implements ITransProcessor {
    private TransContext transContext;
    private ITransProcessor startProcessor;
    @Override
    public void process(TransContext transContext) throws Throwable {
        ITransProcessor exceptionProcessor = null;//异常处理类
        ProcessException transException = this.getClass().getAnnotation(ProcessException.class);
        if(transException!=null){//异常处理类获取
            exceptionProcessor =(ITransProcessor) BeanUtils.getBean(transException.exceptionProcessor());
        }
        try{
            ITransProcessor proxy = (ITransProcessor) Proxy.newProxyInstance(ITransProcessor.class.getClassLoader(),
                    new Class[] {ITransProcessor.class}, new ITransProcessorProxy(this));
            beforeProcessorHandle(transContext);
            proxy.processHandle(transContext);//动态代理处理
            afterProcessorHandle(transContext);
            ITransProcessor nextProcessor = ProcessorContainer.get(
                    BeanUtils.getClassNameLowercase(this.getClass()));//这个this.getclass()表示调用该抽象类的那个对象的class
            if(nextProcessor!=null){
                nextProcessor.process(transContext);
            }else{
                System.out.println("交易结束！");
            }
        }catch (Exception e){
            if (exceptionProcessor != null) {
                exceptionProcessor.process(transContext);
            } else {
                throw e;
            }
        }
    }

    public void afterProcessorHandle(TransContext transContext) {

    }

    public void beforeProcessorHandle(TransContext transContext) {

    }

    @Override
    public void setTransContext(TransContext transContext) {
        this.transContext = transContext;
    }

    @Override
    public void setStartTransProcessor(ITransProcessor startProcessor) {
        this.startProcessor = startProcessor;
    }
}
