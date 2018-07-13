//package com.shalimov.onlineshop.service.impl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ServiceLocator {
//    private static final Map<Class<?>, Object> REGISTRY = new HashMap<>();
//
//    public static void registerService(Class<?> serviceClass, Object service) {
//        REGISTRY.put(serviceClass, service);
//    }
//
//    public static <T> T getService(Class<T> serviceClass) {
//        return serviceClass.cast(REGISTRY.get(serviceClass));
//    }
//}
