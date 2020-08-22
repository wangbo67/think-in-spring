package spring.bean.dependency.injection;

import ioc.overview.beans.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

/**
 * @program: think-in-spring
 * @description: 延迟依赖注入示例
 * @author: wangbo67@github.com
 * @created: 2020-08-20 17:49
 **/
public class LazyAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> objectProvider;

    @Autowired
    private ObjectFactory<Collection<User>> objectFactory;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类 -> spring bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-injection-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.refresh();
        LazyAnnotationDependencyInjectionDemo demo =
                applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println("实时注入：" + demo.user);
        System.out.println("ObjectProvider 延迟注入：" + demo.objectProvider.getObject());
        demo.objectProvider.forEach(System.out::println);
        System.out.println("ObjectFactory 延迟注入：" + demo.objectFactory.getObject());

        applicationContext.close();
    }
}
