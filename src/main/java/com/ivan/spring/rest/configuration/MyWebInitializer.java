package com.ivan.spring.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//так как мы конфигурируем файл с помощтю java класса надо создавать класс потомок с таким длинным названием
public class MyWebInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {
//не понимаю для чего просто сказали поменять на null
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }
    //ссылка на конфигурационный файл дается так
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }
    //этот метод отвечает за то куда указывает и на какой url направляет
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
