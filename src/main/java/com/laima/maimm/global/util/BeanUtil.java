package com.laima.maimm.global.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author jiayi.zhang
 *
 */
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
		return context;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtil.context = applicationContext;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> tClass) {
        return context.getBean(beanName, tClass);
    }

    public static <T> T getBean(Class<T> tClass) {
        return context.getBean(tClass);
    }
}
