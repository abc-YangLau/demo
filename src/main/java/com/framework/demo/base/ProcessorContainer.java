package com.framework.demo.base;

import com.framework.demo.base.ITransProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * 多线程使用的，使用线程安全变量
 * 使用后要及时清理
 */
public class ProcessorContainer {
    private static ThreadLocal<Map<String, ITransProcessor>> nextProcessorContainer =
            new ThreadLocal<Map<String, ITransProcessor>>(){
                protected Map<String,ITransProcessor> initialValue(){
                    return new HashMap<String,ITransProcessor>();
                }
            };

    /**
     * 设置
     * @param currentBeanName
     * @param nextProcessor
     */
    public static void put(String currentBeanName,ITransProcessor nextProcessor){
        nextProcessorContainer.get().put(currentBeanName,nextProcessor);
    }
    public static ITransProcessor get(String currentBeanName){
        return nextProcessorContainer.get().get(currentBeanName);
    }

    /**
     * 释放线程安全变量
     */
    public static void remove(){
        nextProcessorContainer.remove();
    }
}
