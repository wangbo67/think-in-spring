package spring.bean.instantiation;

import ioc.overview.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: think-in-spring
 * @description: bean 的实例化
 * @author: wangbo67@github.com
 * @created: 2020-08-12 15:48
 **/
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context.xml");
        System.out.println("静态方法实例化 bean" + beanFactory.getBean("user-by-static-method", User.class));
        System.out.println("通过 FactoryBean 实例化 bean" + beanFactory.getBean("user-by-factory-bean", User.creatUser()));
    }
}
