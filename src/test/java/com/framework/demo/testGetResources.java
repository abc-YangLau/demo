package com.framework.demo;


import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

public class testGetResources {
    public static void main(String[] args) {
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] metaInfResources = resourcePatternResolver.getResources("classpath:com/framework/tables/**/*DAO/*.xml");
            for (Resource r : metaInfResources) {
                System.out.println("URL:" + r.getURL());
            }
            System.out.println(new PathMatchingResourcePatternResolver().getResources("classpath:com/framework/tables/**/*DAO/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
