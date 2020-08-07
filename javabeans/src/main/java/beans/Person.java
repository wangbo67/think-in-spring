package beans;

/**
 * @program: think-in-spring
 * @description: POJO
 * @author: wangbo67@github.com
 * @created: 2020-08-06 23:01
 **/
public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
