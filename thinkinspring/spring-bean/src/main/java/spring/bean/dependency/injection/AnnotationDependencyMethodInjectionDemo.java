package spring.bean.dependency.injection;

import ioc.overview.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @program: think-in-spring
 * @description: 基于 Java 注解的方法依赖注入示例
 * @author: wangbo67@github.com
 * @created: 2020-08-18 23:51
 **/
public class AnnotationDependencyMethodInjectionDemo {
    private UserHolder userHolder;
    private UserHolder userHolderOfResource;

    @Autowired
    public void init1(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void init2(UserHolder userHolderOfResource) {
        this.userHolderOfResource = userHolderOfResource;
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类 -> spring bean
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/bean-injection-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.refresh();
        AnnotationDependencyMethodInjectionDemo demo =
                applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
        System.out.println("@Autowired 注入：" + demo.userHolder);

        System.out.println("@Resource 注入：" + demo.userHolderOfResource);

        System.out.println(demo.userHolder == demo.userHolderOfResource);

        // 注解方式注入
        System.out.println("构造器注入：" + applicationContext.getBean("userHolder"));
        System.out.println(demo.userHolder == applicationContext.getBean("userHolder"));
        applicationContext.close();
    }
}
