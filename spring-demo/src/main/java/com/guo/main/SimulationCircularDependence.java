package com.guo.main;

import com.guo.dto.A;
import com.guo.dto.B;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 模拟循环依赖
 *
 * Spring中实现循环依赖的三个类
 * singletonObjects : 俗称"单例池" 缓存创建完成单例Bean的地方
 * singletonFactories : 映射创建Bean的原始工厂
 * earlySingletonObjects : 映射Bean的早期引用,这里面的Bean是不完整的,只是一个Instance
 *
 * 后面两个Map中的数据只是过渡使用,创建完成之后就会清理掉数据
 *
 * @author: guofengbo
 * @date: 2020-05-08 10:41
 **/
public class SimulationCircularDependence {

    /**
     * 放置好的bean map
     */
    private static Map<String, Object> cacheMap = new HashMap<>(2);

    public static void main(String[] args) throws Exception {
        //模拟spring扫描出的对象
        Class[] classes = {A.class, B.class};
        //模拟初始化spring初始话bean
        for (Class clazz : classes) {
            getBean(clazz);
        }

        //check
        System.out.println(getBean(B.class).getA() == getBean(A.class));
        System.out.println(getBean(A.class).getB() == getBean(B.class));
    }

    private static <T> T getBean(Class<T> beanClass) throws Exception {
        //用类名小写 简单代bean的命名规则
        String beanName = beanClass.getSimpleName().toLowerCase();

        //如果已经是一个Bean 直接返回
        if (cacheMap.containsKey(beanName)) {
            return (T) cacheMap.get(beanName);
        }

        //实例化对象本身
        Object object = beanClass.getDeclaredConstructor().newInstance();
        //放入缓存
        cacheMap.put(beanName, object);
        //把所有字段当成需要注入的bean,创建并注入到当前bean中
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            //获取需要注入字段的class
            Class<?> fieldClass = field.getType();
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            //如果需要注入的bean,已经在缓存map中,那么把缓存Map中的值注入到改field即可  如果没有缓存继续创建
            field.set(object, cacheMap.containsKey(fieldBeanName) ? cacheMap.get(fieldBeanName) : getBean(fieldClass));
        }
        //属性填充完成,返回
        return (T) object;
    }
}
