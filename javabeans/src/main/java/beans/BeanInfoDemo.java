package beans;

import java.beans.*;
import java.util.stream.Stream;

/**
 * @program: think-in-spring
 * @description: BeanInfo示例
 * @author: wangbo67@github.com
 * @created: 2020-08-06 22:59
 **/
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        // Java Beans 的自省机制
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        // PropertyDescriptor 在Spring框架中应用非常广泛
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    // PropertyDescriptor 允许添加属性编辑器 -> PropertyEditor
                    // GUI -> text(String) -> PropertyType
                    // Person -> name -> String
                    // Person -> age -> Integer
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    String propertyName = propertyDescriptor.getName();
                    if("age".equals(propertyName)) { // 为 age 字段或属性增加 PropertyEditor
                        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                        propertyDescriptor.createPropertyEditor(new Person("dog", 7));
                        System.out.println(propertyType.getTypeName());
                    }
                }
                );
    }

    // spring 3.0 之前类型配置、转换等大量基于 PropertyEditorSupport 操作
    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            if (text instanceof String) {
                setValue(Integer.valueOf(text));
                return;
            }
            throw new java.lang.IllegalArgumentException(text);
        }
    }
}