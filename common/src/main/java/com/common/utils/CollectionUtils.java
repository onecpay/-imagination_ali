package com.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/** @author admin */
public class CollectionUtils {

  public static boolean isEmpty(Collection<?> dataSource) {
    return null == dataSource || dataSource.isEmpty();
  }

  public static boolean isNotEmpty(Collection<?> dataSource) {
    return !isEmpty(dataSource);
  }

  public static <T> T findFirst(List<T> dataSource) {
    return CollectionUtils.isNotEmpty(dataSource) ? dataSource.get(0) : null;
  }

  @SuppressWarnings("unchecked")
  public static <T, E> void copyCollections(
      List<T> dataSource, List<E> targetSource, Class<?> targetClazz) {
    for (T obj : dataSource) {
      try {
        E rsp = (E) targetClazz.newInstance();
        BeanUtils.copyProperties(obj, rsp);
        targetSource.add(rsp);
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 转换Collection包含的对象类型
   *
   * @param dataSource
   * @param targetClazz
   * @param ignoreProperties
   * @param <T>
   * @param <E>
   * @return
   */
  public static <T, E> List<E> translateCollection(
      List<T> dataSource, Class<E> targetClazz, String... ignoreProperties) {
    if (isNotEmpty(dataSource)) {
      List<E> targetSource = new ArrayList<>(20);
      for (T obj : dataSource) {
        try {
          E rsp = targetClazz.newInstance();
          BeanUtils.copyProperties(obj, rsp, ignoreProperties);
          targetSource.add(rsp);
        } catch (InstantiationException | IllegalAccessException e) {
          e.printStackTrace();
        }
      }
      return targetSource;
    } else {
      return Collections.emptyList();
    }
  }
}
