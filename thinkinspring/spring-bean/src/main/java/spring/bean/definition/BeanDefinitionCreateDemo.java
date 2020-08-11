package spring.bean.definition;

import ioc.overview.beans.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @program: think-in-spring
 * @description: bean 构建示例
 * @author: wangbo67@github.com
 * @created: 2020-08-11 22:49
 **/
public class BeanDefinitionCreateDemo {
    public static void main(String[] args) {
        // 1.通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", "11")
                .addPropertyValue("name", "snake")
                .addPropertyValue("age", 11);
        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        // BeanDefinition 并非 bean 的终态，可自定义修改

        // 2.通过 AbstractBeanDefinition 及派生类
        AbstractBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id", "11")
                .add("name", "snake")
                .add("age", 11);
        definition.setPropertyValues(propertyValues);

    }
}