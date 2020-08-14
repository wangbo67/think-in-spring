package spring.bean.dependency.lookup;

import ioc.overview.beans.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: think-in-spring
 * @description: 依赖查找类型安全性示例
 * @author: wangbo67@github.com
 * @created: 2020-08-14 18:15
 **/
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderGetIfAvailable(applicationContext);

        applicationContext.close();
    }

    // 不安全
    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printException("BeanFactory#getBean()", () -> beanFactory.getBean(User.class));
    }

    // 不安全
    public static void displayObjectFactoryGetObject(BeanFactory beanFactory) {
        // ObjectProvider is ObjectFactory
        ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
        printException("ObjectFactory#getObject()", () -> objectFactory.getObject());
    }

    // 安全
    public static void displayObjectProviderGetIfAvailable(BeanFactory beanFactory) {
        ObjectProvider<User> objectProvider = beanFactory.getBeanProvider(User.class);
        printException("ObjectProvider#getIfAvailable()", () -> objectProvider.getIfAvailable());
    }

    public static void printException(String exceptionSource, Runnable runnable) {
        System.err.println("异常来源：" + exceptionSource);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
