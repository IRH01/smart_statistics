package com.hhly.smartdata.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 工具类 - Spring
 */
public class SpringUtil implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 根据Bean名称获取实例
     *
     * @param name Bean注册名称
     * @return bean实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException{
        return applicationContext.getBean(name);
    }

    /**
     * 复制一个bean的属性到另一个bean （重载）
     *
     * @param source 源bean
     * @param target 目标bean
     * @throws org.springframework.beans.BeansException
     */
    public static void copyProperties(Object source, Object target) throws org.springframework.beans.BeansException{
        copyProperties(source, target, new String[]{});
    }

    /**
     * 复制一个bean的属性到另一个bean  （重载）
     *
     * @param source           源bean
     * @param target           目标bean
     * @param ignoreProperties 不需要复制的属性列表
     * @throws org.springframework.beans.BeansException
     */
    public static void copyProperties(Object source, Object target, String[] ignoreProperties) throws org.springframework.beans.BeansException{
        copyProperties(source, target, ignoreProperties, false);
    }

    /**
     * 复制一个bean的属性到另一个bean （重载）
     *
     * @param source     源bean
     * @param target     目标bean
     * @param properties 需要复制的或者不需要复制的属性列表
     * @param isInclude  需要复制的属性？
     * @throws org.springframework.beans.BeansException
     */
    @SuppressWarnings("rawtypes")
    public static void copyProperties(Object source, Object target, String[] properties, boolean isInclude) throws org.springframework.beans.BeansException{
        List<String> ignorePropertiesList = new ArrayList<String>();
        if(!isInclude)
            Collections.addAll(ignorePropertiesList, properties);
        else{
            PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
            List propertiesList = (properties != null) ? Arrays.asList(properties) : new ArrayList<String>();
            for(PropertyDescriptor targetPd : targetPds){
                if(!propertiesList.contains(targetPd.getName()))
                    ignorePropertiesList.add(targetPd.getName());
            }
        }
        Collections.addAll(ignorePropertiesList, "id", "createDate", "createUser", "modifyDate", "modifyUser", "version");
        BeanUtils.copyProperties(source, target, ignorePropertiesList.toArray(new String[0]));
    }

    /**
     * 获取某个属性的值
     *
     * @param beanObj bean对象
     * @param attr    属性
     * @return Object值
     */
    public static Object getBeanAttributeValue(Object beanObj, String attr){
        Object value = null;
        PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(beanObj.getClass(), attr);
        if(pd != null && pd.getReadMethod() != null){
            Method readMethod = pd.getReadMethod();
            try{
                if(readMethod != null){
                    if(!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())){
                        readMethod.setAccessible(true);
                    }
                    value = readMethod.invoke(beanObj);
                }
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }catch(InvocationTargetException e){
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 获取所有属性的值对应的Map(属性,值)
     *
     * @param beanObj bean对象
     * @return Map
     */
    public static Map<String, Object> getBeanAttributes(Object beanObj){
        Map<String, Object> valuesMap = new HashMap<String, Object>();
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(beanObj.getClass());
        for(PropertyDescriptor pd : pds){
            Method readMethod = pd.getReadMethod();
            try{
                if(readMethod != null){
                    if(!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())){
                        readMethod.setAccessible(true);
                    }
                    Object value = readMethod.invoke(beanObj);
                    valuesMap.put(pd.getName(), value);
                }
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }catch(InvocationTargetException e){
                e.printStackTrace();
            }
        }
        return valuesMap;
    }

    /**
     * 设置某个属性的值
     *
     * @param beanObj bean对象
     * @param attr    属性名
     * @param value   值
     */
    public static void setBeanAttributeValue(Object beanObj, String attr, Object value){
        PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(beanObj.getClass(), attr);
        if(pd != null && pd.getReadMethod() != null){
            Method writeMethod = pd.getWriteMethod();
            if(writeMethod == null) return;
            try{
                if(!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())){
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(beanObj, value);
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }catch(InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置所有属性的值
     *
     * @param beanObj   bean对象
     * @param valuesMap 属性-值Map
     */
    public static void setBeanAttributes(Object beanObj, Map<String, Object> valuesMap){
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(beanObj.getClass());
        for(PropertyDescriptor pd : pds){
            if(!valuesMap.containsKey(pd.getName())) continue;
            Object value = valuesMap.get(pd.getName());
            Method writeMethod = pd.getWriteMethod();
            if(writeMethod == null) continue;
            try{
                if(!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())){
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(beanObj, value);
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }catch(InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }
}