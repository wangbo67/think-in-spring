package spring.bean.factory;

import ioc.overview.beans.User;

/**
 * @program: think-in-spring
 * @description: bean 带有默认实现的抽象工厂类
 * @author: wangbo67@github.com
 * @created: 2020-08-12 15:59
 **/
public interface IUserFactory {
    /**
    * @Description: java 8 开始接口默认实现
    * @Param:
    * @return:
    * @Author: wangbo67@github.com
    * @Date: 2020/8/12
    */
    default public User creatUser() {
        return User.creatUser();
    }

    void initUserFactory();

    void doDestroy();
}
