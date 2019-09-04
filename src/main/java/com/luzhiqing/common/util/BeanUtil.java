package com.luzhiqing.common.util;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeanUtil {

    private static MapperFactory mapperFactory;


    @Autowired
    public BeanUtil(MapperFactory mapperFactory) {
        BeanUtil.mapperFactory = mapperFactory;
    }

    public static <T> T map(Object obj, Class<T> target) {
        return mapperFactory.getMapperFacade().map(obj, target);
    }

    public static <D, T> List<T> mapAsList(List<D> obj, Class<T> target) {
        return mapperFactory.getMapperFacade().mapAsList(obj, target);
    }
}

