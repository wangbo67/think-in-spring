package ioc.overview.repository;

import ioc.overview.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @program: think-in-spring
 * @description: 用户信息仓库
 * @author: wangbo67@github.com
 * @created: 2020-08-09 00:21
 **/
public class UserRepository {
    private Collection<User> users; // 依赖注入 ---> 自定义 bean

    private BeanFactory beanFactory; // 依赖注入 ---> 内部的非 bean 对象（內建依赖）

    private ObjectFactory<User> userObjectFactory;

    private ObjectFactory<ApplicationContext> applicationContextObjectFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<User> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<User> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

    public ObjectFactory<ApplicationContext> getApplicationContextObjectFactory() {
        return applicationContextObjectFactory;
    }

    public void setApplicationContextObjectFactory(ObjectFactory<ApplicationContext> applicationContextObjectFactory) {
        this.applicationContextObjectFactory = applicationContextObjectFactory;
    }
}
