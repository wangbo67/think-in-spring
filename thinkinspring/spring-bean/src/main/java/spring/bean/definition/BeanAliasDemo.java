package spring.bean.definition;

import ioc.overview.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: think-in-spring
 * @description: bean 别名示例
 * @author: wangbo67@github.com
 * @created: 2020-08-11 23:54
 **/
public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-definition-context.xml");
        // 通过别名获取 bean 实例
        User systemAUser = beanFactory.getBean("systemAUser", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user == systemAUser);
    }
}
