package spring.bean.factory;

import ioc.overview.beans.User;

/**
 * @program: think-in-spring
 * @description: bean 工厂类
 * @author: wangbo67@github.com
 * @created: 2020-08-12 15:59
 **/
public class UserFactory {
    public User creatUser() {
        return User.creatUser();
    }
}
