package spring.bean.initialization;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.bean.factory.DefaultUserFactory;
import spring.bean.factory.IUserFactory;

/**
 * @program: think-in-spring
 * @description: bean 的初始化
 * @author: wangbo67@github.com
 * @created: 2020-08-12 17:16
 **/
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean(IUserFactory.class));
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
    public IUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
