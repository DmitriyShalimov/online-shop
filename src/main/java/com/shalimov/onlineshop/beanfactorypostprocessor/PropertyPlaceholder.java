package com.shalimov.onlineshop.beanfactorypostprocessor;

import ua.shalimov.ioc.context.beanpostprocessor.BeanFactoryPostProcessor;
import ua.shalimov.ioc.model.BeanDefinition;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertyPlaceholder implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(List<BeanDefinition> list) {
        for (BeanDefinition beanDefinition : list) {
            Map<String, String> dependencies = beanDefinition.getDependencies();
            for (String dependencyKey : dependencies.keySet()) {
                String dependencyValue = dependencies.get(dependencyKey);
                if (dependencyValue.startsWith("${")) {
                    String tempDependencyValue = dependencyValue.substring(2, dependencyValue.length() - 1);
                    Properties property = new Properties();
                    try {
                        FileInputStream inputStream = new FileInputStream("src/main/resources/dao.properties");
                        property.load(inputStream);
                        dependencyValue = property.getProperty(tempDependencyValue);
                        dependencies.put(dependencyKey, dependencyValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
