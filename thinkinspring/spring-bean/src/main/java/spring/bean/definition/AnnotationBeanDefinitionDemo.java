package spring.bean.definition;

import ioc.overview.beans.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: think-in-spring
 * @description: 注解方式注册 BeanDefinition
 * @author: wangbo67@github.com
 * @created: 2020-08-12 10:16
 **/

@Import(AnnotationBeanDefinitionDemo.MyConfig.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration class （配置类，相当于 xml 文件）
        // 4.Java API 配置元信息注册 -> 配置类方式：AnnotatedBeanDefinitionReader#register(Class...)
        applicationContext.register(MyConfig.class);
        // 5.Java API 配置元信息注册 -> 命名/非命名
        registerBeanDefinition(applicationContext, "systemCUser", User.class);
        // 启动上下文
        applicationContext.refresh();
        System.out.println("通过 Java API 配置元信息注册 -> 命名/非命名 的方式注册 bean" + applicationContext.getBean("systemCUser"));
        // 1.通过 @Bean 注册
        System.out.println("通过 @Bean 的方式注册 bean" + applicationContext.getBean("user") == applicationContext.getBean("systemBUser"));

        // 2.通过 @Component 注册
        System.out.println("通过 @Component 的方式注册 bean" + applicationContext.getBean(MyConfig.class));

        // 3.通过 @Import 注册
        System.out.println("通过 @Import 的方式注册 bean" +applicationContext.getBeansOfType(MyConfig.class));
        System.out.println("通过 @Import 的方式注册 bean" +applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    /**
    * @Description: Java API 注册 BeanDefinition
    * @Param:
    * @return:
    * @Author: wangbo67@github.com
    * @Date: 2020/8/12
    */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        // 构建 BeanDefinition
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        builder.addPropertyValue("id", "13")
                .addPropertyValue("name", "goat")
                .addPropertyValue("age", 13L);
        // 注册 BeanDefinition
        if(StringUtils.hasText(beanName)) {
            // 命名的方式
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            // 非命名的方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

    // 定义当前类作为 Spring bean
    @Component
    public static class MyConfig {
        /**
        * @Description: 通过 java 注解定义一个 bean
        * @Param: 
        * @return: 
        * @Author: wangbo67@github.com
        * @Date: 2020/8/12
        */
        @Bean(name = {"user", "systemBUser"})
        public User user() {
            User user = new User();
            user.setId("12");
            user.setName("fish");
            user.setAge(12L);
            return user;
        }
    }
}
