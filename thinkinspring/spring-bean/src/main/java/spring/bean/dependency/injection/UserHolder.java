package spring.bean.dependency.injection;

import ioc.overview.beans.User;

/**
 * @program: think-in-spring
 * @description:
 * @author: wangbo67@github.com
 * @created: 2020-08-19 00:08
 **/
public class UserHolder {
    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
