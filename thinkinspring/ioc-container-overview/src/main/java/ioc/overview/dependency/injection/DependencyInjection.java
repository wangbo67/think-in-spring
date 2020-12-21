package ioc.overview.dependency.injection;

import ioc.overview.beans.User;
import ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @program: think-in-spring
 * @description: 依赖注入示例
 * @author: wangbo67@github.com
 * @created: 2020-08-07 15:59
 **/
public class DependencyInjection {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-injection-context.xml");

        // 依赖注入 ---> 自定义 bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers());

        // 依赖注入 ---> 內建依赖
        BeanFactory userBeanFactory = userRepository.getBeanFactory();
        System.out.println(userBeanFactory);//实际是DefaultListableBeanFactory
        System.out.println(userBeanFactory == beanFactory);

        // 依赖查找(错误) No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available
        // 依赖查找1
        // System.out.println(userRepository.getBeanFactory().getBean(BeanFactory.class));
        // 依赖查找2
        // System.out.println(beanFactory.getBean(BeanFactory.class));
        // 从依赖查找1、2结果看出，依赖注入和依赖查找数据源并不一样；BeanFactory 并不是一个普通的 bean。

        // 依赖注入 ---> 延迟
        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
        System.out.println(userObjectFactory.getObject());

        ObjectFactory<ApplicationContext> applicationContextObjectFactory = userRepository.getApplicationContextObjectFactory();
        BeanFactory applicationContext = applicationContextObjectFactory.getObject();
        System.out.println(applicationContext);
        System.out.println(applicationContext == beanFactory); // ClassPathXmlApplicationContext is a BeanFactory
    }
}