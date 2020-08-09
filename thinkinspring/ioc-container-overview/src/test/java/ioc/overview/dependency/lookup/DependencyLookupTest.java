package ioc.overview.dependency.lookup;

import ioc.overview.beans.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: think-in-spring
 * @description: 依赖查找示例
 * 1.通过名称查找
 * @author: wangbo67@github.com
 * @created: 2020-08-07 15:59
 **/
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:META-INF/dependency-lookup-context.xml"}) //加载配置文件
public class DependencyLookupTest {
    private BeanFactory beanFactory = null;

    @Before
    public void init() {
        beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
    }

    @Test
    public void test() {
        Assert.assertTrue(beanFactory instanceof ListableBeanFactory);
        lookupInLazy(beanFactory);
        lookupInRealTime(beanFactory);
    }

    /**
     * @Description: 延迟查找bean
     * @Param:
     * @return:
     * @Author: wangbo67@github.com
     * @Date: 2020/8/7
     */
    public void lookupInLazy(BeanFactory beanFactory) {
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
    public void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找---" + user);
    }
}