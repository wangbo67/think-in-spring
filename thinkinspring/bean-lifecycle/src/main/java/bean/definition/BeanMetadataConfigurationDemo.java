package bean.definition;

import ioc.overview.beans.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @program: think-in-spring
 * @description: 基于 Properties 的 bean 元信息配置示例
 * @author: wangbo67@github.com
 * @created: 2020-09-01 16:48
 **/
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader definitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "META-INF/user.properties";
        Resource source = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(source, "UTF-8");
        int beanDefinitionsCount = definitionReader.loadBeanDefinitions(encodedResource);
        System.out.println(beanDefinitionsCount);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
