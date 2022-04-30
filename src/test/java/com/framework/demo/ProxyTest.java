package com.framework.demo;

import com.framework.demo.base.ITransProcessor;
import com.framework.demo.base.TransContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;

public class ProxyTest {
    public static void main(String[] args) throws Throwable {
        //加入这一段可以在磁盘中生成 代理类，让我们看到代理类的真面目
//            System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        TransContext trans = new TransContext();
        trans.setAmtTr(new BigDecimal(100));
        trans.setDateReq("20210719");
        TransProcessTest source = new TransProcessTest();

        ITransProcessor proxyTest = (ITransProcessor) Proxy.newProxyInstance(TransProcessTest.class.getClassLoader(),
                new Class[]{ITransProcessor.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("processHandle".equals(method.getName())) {
                            return method.invoke(source, args);
                        } else if ("process".equals(method.getName())) {
                            return method.invoke(source, args);
                        }
                        return null;
                    }
                });

        System.out.println(proxyTest instanceof Proxy);//proxyTest是Proxy的实例，实现了ITransProcessor接口
        System.out.println("proxyTest的class类：" + proxyTest.getClass().toString());
        Field[] fields = proxyTest.getClass().getDeclaredFields();
        for (Field f : fields) {
            System.out.println("属性：" + f.getName());
        }
        Method[] methods = proxyTest.getClass().getDeclaredMethods();
        for (Method method : methods
        ) {
            System.out.println("方法：" + method.getName());
        }
        Class<?>[] interfaces = proxyTest.getClass().getInterfaces();
        for (Class<?> inteface : interfaces
        ) {
            System.out.println("实现的接口：" + inteface.getSimpleName());
        }
        System.out.println("实现的父类：" + proxyTest.getClass().getSuperclass());
        System.out.println("类加载器：" + proxyTest.getClass().getClassLoader());
        proxyTest.processHandle(trans);
        proxyTest.process(trans);
    }


}
