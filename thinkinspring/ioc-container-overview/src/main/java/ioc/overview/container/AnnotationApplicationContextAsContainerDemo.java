package ioc.overview.container;

import ioc.overview.beans.User;
import ioc.overview.beans.VipUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @program: think-in-spring
 * @description: ApplicationContext 容器示例
 * @author: wangbo67@github.com
 * @created: 2020-08-10 23:10
 **/
public class AnnotationApplicationContextAsContainerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类
        applicationContext.register(AnnotationApplicationContextAsContainerDemo.class);
        applicationContext.refresh();
        lookupByType(applicationContext);
        applicationContext.close();
    }

    @Bean
    public User setUser() {
        User user = new User();
        user.setId("4");
        user.setName("pig");
        user.setAge(8L);
        return user;
    }

    @Bean
    public VipUser vipUser() {
        VipUser vipUser = new VipUser();
        vipUser.setId("5");
        vipUser.setName("snake");
        vipUser.setAge(10L);
        return vipUser;
    }

    private static void lookupByType(ApplicationContext applicationContext) {
        System.out.println(applicationContext.getBeansOfType(User.class));
        // 有错
        // System.out.println(applicationContext.getBean(User.class));
    }
}
