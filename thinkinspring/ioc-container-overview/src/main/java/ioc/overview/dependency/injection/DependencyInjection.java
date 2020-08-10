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
        if (null == beanFactory) {
            return;
        }
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);

        // BeanFactory 和 ApplicationContext whoIsIocContainer
        whoIsIocContainer(beanFactory, userRepository);

        // 依赖来源1 ---> 自定义 bean
        System.out.println(userRepository.getUsers());

        // 1.依赖注入 依赖来源2 ---> 内建的依赖
        System.out.println(userRepository.getBeanFactory()); // DefaultListableBeanFactory
        // 2.依赖注入(错误)
        // System.out.println(userRepository.getBeanFactory().getBean(BeanFactory.class));
        // System.out.println(beanFactory.getBean(BeanFactory.class)); //No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available
        // 从1、2结果看出，依赖注入和依赖查找数据源并不一样；BeanFactory 并不是一个普通的 bean。

        // 依赖来源3 ---> 容器内建 bean 对象
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);

        // 依赖注入 -- 延迟
        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
        System.out.println(userObjectFactory.getObject());

        ObjectFactory<ApplicationContext> applicationContextObjectFactory = userRepository.getApplicationContextObjectFactory();
        BeanFactory beanFactory1 = applicationContextObjectFactory.getObject();
        System.out.println(beanFactory1);
        System.out.println(beanFactory1 == beanFactory);
    }

    private static void whoIsIocContainer(BeanFactory beanFactory, UserRepository userRepository) {
        // 这个表达式为什么不成立
        // ClassPathXmlApplicationContext <- AbstractRefreshableApplicationContext <- AbstractApplicationContext <- ConfigurableApplicationContext <- ApplicationContext <- BeanFactory
        // AbstractRefreshableApplicationContext 中组合了 DefaultListableBeanFactory，和 ClassPathXmlApplicationContext 并不是完全的抽象或者继承 DefaultListableBeanFactory

        // 为什么 UserRepository 注入的 BeanFactory 不等于 ClassPathXmlApplicationContext 得到的 BeanFactory？
        // 是因为 AbstractApplicationContext#prepareBeanFactory 中指明了 beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory); 也就是说当 byType 是 BeanFactory.class 的时候，获得是的 ApplicationContext 中的 DefaultListableBeanFactory 对象。
        System.out.println(beanFactory == userRepository.getBeanFactory());

        //ApplicationContext 使用了代理模式，也使用了面门模式，参考AbstractApplicationContext#getBean，实际上代理给了 DefaultListableBeanFactory#getBean
    }
}