package com.test.mail.app.web.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by tigran on 1/7/17.
 */
public class AppViewInitializer /*extends AbstractAnnotationConfigDispatcherServletInitializer*/ {
//    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class};
    }

//    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

//    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
}
