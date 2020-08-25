package com.company;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MethodInterception {
    @Test
    public void annotationValue() {
        MainPage mainPage = createPage(MainPage.class);
        assertNotNull(mainPage);
        assertEquals(mainPage.buttonSearch(), ".//*[@test-attr='button_search']");
        assertEquals(mainPage.textInputSearch(), ".//*[@test-attr='input_search']");
    }

    private MainPage createPage(Class clazz) {

        MainPageImpl mainPageImpl = new MainPageImpl();
        ClassLoader pageClassLoader = mainPageImpl.getClass().getClassLoader();
        Class[] interfaces = mainPageImpl.getClass().getInterfaces();

        MainPage proxyPage = (MainPage) Proxy.newProxyInstance(pageClassLoader, interfaces, new MainPageInvocationHandler(mainPageImpl));

        return proxyPage;
    }
}

