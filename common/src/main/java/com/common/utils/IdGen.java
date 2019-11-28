package com.common.utils;

import com.common.uid.UidGenerator;
import org.springframework.stereotype.Service;

/**
 * 封装各种生成唯一性ID算法的工具类.
 *
 * @author Administrator
 */
@Service
public class IdGen {

  private static UidGenerator uidGenerator = null;

  public static UidGenerator getUidGenerator() {
    if (uidGenerator == null) {
      uidGenerator = SpringContextHolder.getBean(UidGenerator.class);
    }
    return uidGenerator;
  }

  /** 生成ID */
  public static String uuidString() {
    return getUidGenerator().getUid() + "";
  }

  /** 生成Long. */
  public static long uuidLong() {
    return getUidGenerator().getUid();
  }

  /**
   * parseTime
   *
   * @param uid
   * @return
   */
  public static long parseTime(Long uid) {
    return getUidGenerator().parseTime(uid);
  }

  public static String parseUid(long uid) {
    return getUidGenerator().parseUid(uid);
  }
}
