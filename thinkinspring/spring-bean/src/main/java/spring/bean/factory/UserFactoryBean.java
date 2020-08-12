package spring.bean.factory;

import ioc.overview.beans.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @program: think-in-spring
 * @description:
 * @author: wangbo67@github.com
 * @created: 2020-08-12 16:12
 **/
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.creatUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
