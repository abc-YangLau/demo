package com.framework.demo.base;


import com.framework.demo.annotation.TransDescription;
import com.framework.demo.util.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class TransTemplate {
    private static TransTemplate transTemplate = new TransTemplate();

    /**
     * 获取处理模板
     */
    public static TransTemplate getInstance(){
        return transTemplate;
    }
    /**
     *  执行程序
     */
     public void tansExecute(ITransProcessor trans,TransContext transContext){
         trans.setTransContext(transContext);
         initTransList(trans);//初始化处理类
         try{
             trans.process(transContext);
         } catch (Throwable throwable) {
             throwable.printStackTrace();
         }finally {
             ProcessorContainer.remove();
         }
     }

    /**
     * 初始化处理顺序
     * @param trans
     */
    @Bean
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
                        ProcessorContainer.put(trans.getClass().getSimpleName().toLowerCase(),transProcessor);
                    }
                    if((i+1)<transProcessorList.length){
                        ProcessorContainer.put(transProcessorList[i].toLowerCase(),
                                (ITransProcessor) BeanUtils.getBean(transProcessorList[i+1]));
                    }
                }
            }
        }
    }
}
