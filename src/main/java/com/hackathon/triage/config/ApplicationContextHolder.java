package com.hackathon.triage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
public class ApplicationContextHolder {

    private static ApplicationContextHolder INSTANCE;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void intialize() {
        INSTANCE = this;
    }

    public static <T> T getBean(Class<T> inClass) {
        return INSTANCE.applicationContext.getBean(inClass);
    }

    public static Object getBean(String inBeanName) {
        return INSTANCE.applicationContext.getBean(inBeanName);
    }
}
