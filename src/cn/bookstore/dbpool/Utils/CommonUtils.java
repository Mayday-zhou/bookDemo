package cn.bookstore.dbpool.Utils;

import cn.bookstore.pojo.Admin;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {

    /*两个用处：1,生成不重复的id   2,封装一个map放回为Category对象，也就在子分类*/
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }


    public static <T> T toBean(Map map, Class<T> clazz) {
        T bean = null;
        try {
            bean = clazz.getConstructor().newInstance();
            ConvertUtils.register(new DateConverter(),java.util.Date.class);
            BeanUtils.populate(bean,map);   /*存疑，不知道干嘛的，源码没看懂*/
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
