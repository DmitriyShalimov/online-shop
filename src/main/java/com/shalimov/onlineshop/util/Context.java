package com.shalimov.onlineshop.util;

import ua.shalimov.ioc.context.ApplicationContext;
import ua.shalimov.ioc.context.ClassPathApplicationContext;

public class Context {
    private static Context context;
    private final ApplicationContext applicationContext;

    public static Context instance() {
        if (context == null) {
            context = new Context();
        }
        return context;
    }

    private Context() {
        applicationContext = new ClassPathApplicationContext("context.xml");
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
