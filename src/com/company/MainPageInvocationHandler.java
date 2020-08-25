package com.company;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MainPageInvocationHandler implements InvocationHandler {

    private MainPage mainPage;

    public MainPageInvocationHandler(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Selector annotation = method.getAnnotation(Selector.class);
        if (annotation != null) {
            String xpath = annotation.xpath();
            return xpath;
        } else return method.invoke(mainPage, args);
    }
}
