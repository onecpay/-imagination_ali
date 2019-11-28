package com.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 与Spring中的BeanUtils功能等同
 *
 * @author eleven
 */
public abstract class BaseBeanUtil {

  private static final Logger logger = LoggerFactory.getLogger(BaseBeanUtil.class);

  public static boolean isEmpty(Object obj) {
    if (obj == null) {
      return true;
    }
    if (obj instanceof String) {
      if (((String) obj).trim().length() == 0) {
        return true;
      }
    } else if (obj instanceof Collection) {
      if (((Collection) obj).isEmpty()) {
        return true;
      }
    } else if (obj.getClass().isArray()) {
      if (((Object[]) obj).length == 0) {
        return true;
      }
    } else if (obj instanceof Map) {
      if (((Map) obj).isEmpty()) {
        return true;
      }
    } else {
      return false;
    }
    return false;
  }

  public static boolean isNumber(Object obj) {

    if (obj == null) {
      return false;
    }
    if (obj instanceof Number) {
      return true;
    }
    if (obj instanceof String) {
      try {
        Double.parseDouble((String) obj);
        return true;
      } catch (NumberFormatException ex) {
        logger.debug(ex.getMessage());
        return false;
      }
    }
    return false;
  }

  public static boolean isInherit(Class currentClass, Class classParent) {
    return classParent.isAssignableFrom(currentClass);
  }

  private static List<Field> getAllDeclareFields(Class<?> cls) {
    List<Field> list = new ArrayList<>();
    Collections.addAll(list, cls.getDeclaredFields());
    return list;
  }

  static List<String> getAllFieldNames(Class<?> cls) {
    List<String> list = new ArrayList<>();
    List<Field> fields = getAllDeclareFields(cls);
    Collection c = new ArrayList();
    c.containsAll(fields);
    list.addAll(c);
    return list;
  }

  private static Map getBeanMap(Object object) {
    Class<?> beanClass = BaseClassUtil.getTargetClass(object);
    BeanMap.Generator gen = new BeanMap.Generator();
    gen.setBean(object);
    gen.setBeanClass(beanClass);
    return gen.create();
  }

}
