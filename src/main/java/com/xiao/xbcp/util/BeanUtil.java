package com.xiao.xbcp.util;

import com.github.pagehelper.PageInfo;
import com.xiao.xbcp.vo.Page;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * 对象拷贝类
 *
 * @author jiawei
 */
public class BeanUtil {

    protected static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    //public static Map<String, BeanCopier> beanCopierMap = new HashMap<>();

    public static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    static {
        mapperFactory.getConverterFactory().registerConverter(new LocalDateTimeConverter());
        mapperFactory.getConverterFactory().registerConverter(new LocalDateConverter());
        mapperFactory.getConverterFactory().registerConverter(new LocalTimeConverter());
    }

    private static <T> T beanCopy(Object source, Class<T> cls) {
        if (null == source || null == cls) {
            return null;
        }
        return mapperFactory.getMapperFacade().map(source, cls);
    }


    /**
     * @param source src instantiation
     * @param target target instantiation
     * @param <S>    source
     * @param <T>    target
     * @return target
     */
    public static <S, T> T copy(S source, Class<T> target) {
        if (null == source || null == target) {
            return null;
        }
        return beanCopy(source, target);
    }


    /**
     * @param source src instantiation
     * @param target target instantiation
     * @param mapper 在拷贝后需要的额外操作
     * @param <S>    source
     * @param <T>    target
     * @return target
     */
    public static <S, T> T copy(S source, Class<T> target, BiFunction<S, T, T> mapper) {
        if (null == source || null == target) {
            return null;
        }
        return mapper.apply(source, beanCopy(source, target));
    }

    /**
     * @param source bean Page
     * @param cls    model class
     * @param <?>    bean
     * @param <T>    model
     * @return
     */
    public static <T> Page<T> copyPage(PageInfo<?> source, Class<T> cls) {
        if (null == source || null == cls) {
            return Page.emptyPage();
        }
        Page<T> page = copyPageCommon(source);
        List<T> list = new ArrayList<>();
        if (source.getList() != null && !source.getList().isEmpty()) {
            for (Object o : source.getList()) {
                if (null == o) {
                    continue;
                }
                list.add(beanCopy(o, cls));
            }
            page.setList(list);
        } else {
            page.setList(Collections.emptyList());
        }
        return page;
    }

    /**
     * @param source bean Page
     * @param cls    model class
     * @param mapper 在拷贝后需要的额外操作
     * @param <S>    bean
     * @param <T>    model
     * @return
     */
    public static <S, T> Page<T> copyPage(PageInfo<S> source, Class<T> cls, BiFunction<S, T, T> mapper) {
        if (null == source || null == cls) {
            return Page.emptyPage();
        }
        Page<T> page = copyPageCommon(source);
        if (source.getList() != null && !source.getList().isEmpty()) {
            page.setList(source.getList().stream().map(bean -> {
                try {
                    return mapper.apply(bean, beanCopy(bean, cls));
                } catch (Exception e) {
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList()));
        } else {
            page.setList(Collections.emptyList());
        }
        return page;
    }

    public static <S, T> List<T> copyList(List<S> source, Class<T> cls, BiFunction<S, T, T> mapper) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        return source.stream().map(t -> {
            try {
                return BeanUtil.copy(t, cls, mapper);
            } catch (Exception e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T> List<T> copyList(List<?> source, Class<T> cls) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> resultList = new ArrayList<>();
        for (Object o : source) {
            resultList.add(beanCopy(o, cls));
        }
        return resultList;
    }


    public static <T> Page<T> copyPageCommon(PageInfo<?> src) {
        if (src == null) {
            return Page.emptyPage();
        } else {
            Page<T> page = new Page<>();
            page.setTotal(src.getTotal());
            page.setPageSize(src.getPageSize());
            page.setPageNum(src.getPageNum());
            return page;
        }
    }


    private static class LocalDateTimeConverter extends BidirectionalConverter<LocalDateTime, LocalDateTime> {
        @Override
        public LocalDateTime convertTo(LocalDateTime source, Type<LocalDateTime> type, MappingContext mappingContext) {
            return LocalDateTime.from(source);
        }

        @Override
        public LocalDateTime convertFrom(LocalDateTime source, Type<LocalDateTime> type, MappingContext mappingContext) {
            return LocalDateTime.from(source);
        }
    }

    private static class LocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {
        @Override
        public LocalDate convertTo(LocalDate source, Type<LocalDate> type, MappingContext mappingContext) {
            return LocalDate.from(source);
        }

        @Override
        public LocalDate convertFrom(LocalDate source, Type<LocalDate> type, MappingContext mappingContext) {
            return LocalDate.from(source);
        }
    }

    private static class LocalTimeConverter extends BidirectionalConverter<LocalTime, LocalTime> {

        @Override
        public LocalTime convertTo(LocalTime source, Type<LocalTime> type, MappingContext mappingContext) {
            return LocalTime.from(source);
        }

        @Override
        public LocalTime convertFrom(LocalTime source, Type<LocalTime> type, MappingContext mappingContext) {
            return LocalTime.from(source);
        }
    }

}
