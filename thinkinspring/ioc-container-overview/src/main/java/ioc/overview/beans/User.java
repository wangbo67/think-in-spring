package ioc.overview.beans;

import javax.annotation.PostConstruct;

/**
 * @program: think-in-spring
 * @description: 用户示例
 * @author: wangbo67@github.com
 * @created: 2020-08-07 16:04
 **/
public class User {
    private String id;
    private String name;
    private Long age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static User creatUser() {
        return new User();
    }
}
