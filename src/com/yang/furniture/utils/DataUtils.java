package com.yang.furniture.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author 刘洋
 * @date 2022/5/28  3:52 PM
 */
public class DataUtils {
    public static <T> T copyParamToBean(Map paramMap, T bean) {
        try {
            BeanUtils.populate(bean, paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Integer parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            return defaultValue;
        }
    }

    public static String orderIdGenerator(int id) {
        return System.currentTimeMillis() + "" + id;
    }
}
