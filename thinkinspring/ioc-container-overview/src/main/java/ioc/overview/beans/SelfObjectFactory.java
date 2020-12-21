package ioc.overview.beans;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

/**
 * @program: think-in-spring
 * @description: 自定义ObjectFactory
 * @author: wangbo67@github.com
 * @created: 2020-08-07 23:37
 **/
public class SelfObjectFactory implements ObjectFactory {
    public Object getObject() throws BeansException {
        User user = new User();
        user.setId("2");
        user.setName("cat");
        user.setAge(3L);

        return user;
    }
}
