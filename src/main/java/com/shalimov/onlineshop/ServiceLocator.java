package com.shalimov.onlineshop;

import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.service.ProductService;
import com.shalimov.onlineshop.service.UserService;
import ua.shalimov.ioc.context.ApplicationContext;
import ua.shalimov.ioc.context.ClassPathApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static final Map<String,Object> SERVICES=new HashMap<>();

    static {
        ApplicationContext applicationContext = new ClassPathApplicationContext("context.xml");
        SecurityService securityService = (SecurityService) applicationContext.getBean("securityService");
        ProductService productService = (ProductService) applicationContext.getBean("productService");
        UserService userService = (UserService) applicationContext.getBean("userService");
        SERVICES.put("securityService",securityService);
        SERVICES.put("productService",productService);
        SERVICES.put("userService",userService);
    }
    public static Object getService(String serviceName){
        return SERVICES.get(serviceName);
    }
}
