package com.framework.demo.base;


import com.framework.demo.annotation.TransDescription;
import com.framework.demo.util.BeanUtils;

public class TransTemplate {
    private static TransTemplate transTemplate = new TransTemplate();

    /**
     * 获取处理模板
     */
    public static TransTemplate getInstance() {
        return transTemplate;
    }

    /**
     *  执行程序
     */

    public void tansExecute(ITransProcessor trans,TransContext transContext){
         trans.setTransContext(transContext);
         initTransList(trans);//初始化处理类
         try {
             //记录交易状态
             beforeProcessorHandle(transContext);
             //交易核心代码
             trans.process(transContext);
             //更新交易状态
             afterProcessorHandle(transContext);
         } catch (Throwable throwable) {
             //更新交易状态
             transContext.setThrowable(throwable);
             afterProcessorHandle(transContext);
             throwable.printStackTrace();
         }finally {
             ProcessorContainer.remove();
         }
     }

    /**
     * 初始化处理顺序
     * @param trans
     */
    private void initTransList(ITransProcessor trans) {
        TransDescription transDescription = trans.getClass().getAnnotation(TransDescription.class);
        if(transDescription!=null){
            String[] transProcessorList = transDescription.transProcessorList();//获取到注解列表
            if(transProcessorList.length>0){
                ITransProcessor transProcessor = null;
                for(int i=0;i<transProcessorList.length;i++){
                    transProcessor = (ITransProcessor) BeanUtils.getBean(transProcessorList[i]);
                    System.out.println(transProcessor);
                    if(i==0){
                        trans.setStartTransProcessor(transProcessor);
                        ProcessorContainer.put(trans.getClass().getSimpleName().toLowerCase(), transProcessor);
                    }
                    if ((i + 1) < transProcessorList.length) {
                        ProcessorContainer.put(transProcessorList[i].toLowerCase(),
                                (ITransProcessor) BeanUtils.getBean(transProcessorList[i + 1]));
                    }
                }
            }
        }
    }


    public void beforeProcessorHandle(TransContext transContext) {
        System.out.println("记录交易状态");
    }

    public void afterProcessorHandle(TransContext transContext) {
        if (transContext.getThrowable() != null) {
            System.out.println("更新失败交易状态");
        } else {
            System.out.println("更新成功交易状态");
        }
    }


}
