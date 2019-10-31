package com.luzhiqing.common.util;



import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 *
 * bean属性复制<br />2.0修改说明（黄承清）：<br />1.基于可靠性和适用性考虑，由于本工具较为重要，因此本工具类实现方法均基于开源框架dozer实现。<br />
 * 2.本工具继承于spring的BeanUtils。<br />
 * 3.本工具提供的方式主要为指定目的Class，依照dozer自动生成实体规则生成最终的对象，并增加容器类的支持；相较于Spring的BeanUtils，支持不严格的属性类型赋值，减少new目的对象操作<br />
 * 4.不推荐使用dozer注解模式，造成代码侵入
 * 5.map系列方法支持深度复制，敬请留意
 * @author liush
 * @modifier 黄承清
 * @date 2017年4月7日
 * @version 2.0
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    private static final Mapper defaultMapper = DozerBeanMapperBuilder.buildDefault();

    /**
     * 从source对象创建destinationClass的实例，Bean<->Bean，Bean<->Map，容器支持List<->List,List<->Array,Array<->Array,Set<->Set,Set<->Array,Set<->List，容器间复制时存储的对象类型不变
     * <br />支持深度复制
     * @author 史来权
     * @param source
     * @param destinationClass
     * @return
     */
    public static <T> T map(final Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return defaultMapper.map(source, destinationClass);
    }

    /**
     * 两个对象间的复制，Bean<->Bean，Bean<->Map，容器支持List<->List,List<->Array,Array<->Array,Set<->Set,Set<->Array,Set<->List，容器间复制时存储的对象类型不变
     * <br />支持深度复制
     * @author 黄承清
     * @param source
     * @param destination
     */
    public static void map(final Object source, Object destination) {
        if (source == null) {
            return;
        }
        defaultMapper.map(source, destination);
    }

    private static <T> Collection<T> mapAndConvert(final Collection<?> source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        Collection<T> result;
        // 由于source的来源各异，无法确保百分百执行newInstace，因此全部生成新的容器返回
        if (List.class.isAssignableFrom(source.getClass())) {
            result = new ArrayList<T>(0);
        } else if (Set.class.isAssignableFrom(source.getClass())) {
            result = new HashSet<T>(0);
        } else {
            result = new ArrayDeque<T>(0);
        }
        for (Object src : source) {
            result.add(map(src, destinationClass));
        }
        return result;
    }

    /**
     * 容器List间的复制及对象转换
     * <br />支持深度复制
     * @param source
     * @param destinationClass 容器内对象需要转换的目标类型
     * @return
     */
    public static <T> List<T> mapc(final List<?> source, Class<T> destinationClass) {
        return (List<T>) mapAndConvert(source, destinationClass);
    }

    /**
     * 容器Set间的复制及对象转换
     * <br />支持深度复制
     * @param source
     * @param destinationClass 容器内对象需要转换的目标类型
     * @return
     */
    public static <T> Set<T> mapc(final Set<?> source, Class<T> destinationClass) {
        return (Set<T>) mapAndConvert(source, destinationClass);
    }

    /**
     * 容器Queue间的复制及对象转换
     * <br />支持深度复制
     * @param source
     * @param destinationClass 容器内对象需要转换的目标类型
     * @return
     */
    public static <T> Queue<T> mapc(final Queue<?> source, Class<T> destinationClass) {
        return (Queue<T>) mapAndConvert(source, destinationClass);
    }

}

