package ioc.overview.dependency.lookup;

import ioc.overview.annotation.Vip;
import ioc.overview.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @program: think-in-spring
 * @description: 依赖查找示例
 * @author: wangbo67@github.com
 * @created: 2020-08-07 15:59
 **/
public class DependencyLookup {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        if (null == beanFactory) {
            return;
        }
        //1.通过名称查找
        lookupInLazy(beanFactory);
        lookupInRealTime(beanFactory);
        //2.通过类型查找
        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);
        //3.通过注解查找
        lookupByAnnotationType(beanFactory);
    }

    /**
    * @Description: 根据注解查找bean
    * @Param: 
    * @return:
    * @Author: wangbo67@github.com
    * @Date: 2020/8/7
    */
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> userMap = listableBeanFactory.getBeansWithAnnotation(Vip.class);
            System.out.println("按注解查找bean---" + userMap);
        }
    }

    /**
    * @Description: 按类型查找多个bean
    * @Param:
    * @return:
    * @Author: wangbo67@github.com
    * @Date: 2020/8/7
    */
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("按类型查找多个bean---" + userMap);
        }
    }

    /**
    * @Description: 按类型查找单个bean
    * @Param:
    * @return:
    * @Author: wangbo67@github.com
    * @Date: 2020/8/7
    */
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("按类型查找单个bean---" + user);
    }

    /**
    * @Description: 延迟查找bean
    * @Param:
    * @return:
    * @Author: wangbo67@github.com
    * @Date: 2020/8/7
    */
    public static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找---" + user);
    }

    /**
    * @Description: 实时查找bean
    * @Param:
    * @return:
    * @Author: wangbo67@github.com
    * @Date: 2020/8/7
    */
    public static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找---" + user);
    }
}