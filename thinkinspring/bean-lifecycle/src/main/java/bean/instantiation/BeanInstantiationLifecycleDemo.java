package bean.instantiation;

import ioc.overview.beans.User;
import ioc.overview.beans.VipUser;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * @program: think-in-spring
 * @description: bean 的实例化示例
 * @author: wangbo67@github.com
 * @created: 2020-09-03 15:33
 **/
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加自定义 BeanPostProcessor 实例
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("META-INF/dependency-lookup-context.xml");
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        VipUser vipUser = beanFactory.getBean("vipUser", VipUser.class);
        System.out.println(vipUser);
    }

    // 实例前
    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("vipUser", beanName) && ObjectUtils.nullSafeEquals(VipUser.class, beanClass)) {
                // 覆盖 VipUser
                return new VipUser();
            }
            // 保持 Spring IoC 容器的实例化操作
            return null;
        }

        // 实例后
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            // 返回 false，跳过 bean 的 populate 方式，需要手动植入属性；返回 true，按原有的逻辑
            return true;
        }

        // 拦截处理 property
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            return null;
        }
    }
}