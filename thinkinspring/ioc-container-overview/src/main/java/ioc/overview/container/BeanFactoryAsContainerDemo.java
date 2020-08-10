package ioc.overview.container;

import ioc.overview.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @program: think-in-spring
 * @description: BeanFactory 容器示例
 * @author: wangbo67@github.com
 * @created: 2020-08-10 23:10
 **/
public class BeanFactoryAsContainerDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitionsCount = xmlBeanDefinitionReader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");
        System.out.println(beanDefinitionsCount);
        lookupByType(beanFactory);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        System.out.println(beanFactory.getBean(User.class));
    }
}
