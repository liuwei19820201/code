package com.example.demo.common.tools;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * @author lagon
 * @time 2017/3/2 9:44
 * @description 自定义Spring注解扫描Bean名（spring bean id）生成器，默认为类名首字母小写，自定义为类全名
 */
public class CustomAnnotationBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    protected final String buildDefaultBeanName(BeanDefinition definition) {
        return definition.getBeanClassName();
    }

}
