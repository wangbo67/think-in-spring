package spring.bean.initialization;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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

        // 延迟加载，Spring 上下文会在 bean 初始化之前完成启动；非延迟加载则相反。
        System.out.println("Spring 上下文已启动......");
        System.out.println(applicationContext.getBean(IUserFactory.class));
        System.out.println("Spring 上下文准备关闭......");
        applicationContext.close();
        System.out.println("Spring 上下文已关闭......");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    //@Lazy
    //@Lazy(value = false)
    public IUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
