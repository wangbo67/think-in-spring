package spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @program: think-in-spring
 * @description: 抽象工厂类的默认实现类
 * @author: wangbo67@github.com
 * @created: 2020-08-12 16:01
 **/
public class DefaultUserFactory implements IUserFactory, InitializingBean {
    // 1.java 标准注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct 方式初始化 bean ...");
    }

    // 2.实现接口方法 InitializingBean#afterPropertiesSet()
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() 方式初始化 bean ...");
    }

    // 3.自定义初始化方法
    public void initUserFactory() {
        System.out.println("initUserFactory 方式初始化 bean ...");
    }
}
