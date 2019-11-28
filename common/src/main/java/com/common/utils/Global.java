package com.common.utils;

import com.google.common.base.Strings;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 全局配置类
 *
 * @author yangzhongying
 * @version 2016-11-25
 */
public class Global {

  /**
   * 根据key获取配置信息
   *
   * @param key 键
   * @return String
   */
  public static String getConfig(String key) {
    return SpringContextHolder.getBean(ConfigurableEnvironment.class).getProperty(key);
  }

  /**
   * 判断是否是开发模式
   *
   * @return boolean
   */
  public static boolean isDevMode() {
    String devMode = getConfig("platform.dev.mode");
    return Strings.isNullOrEmpty(devMode) || "true".equals(devMode) || "1".equals(devMode);
  }

  /** 是否支持sso单点登录 */
  public static boolean enableSSO() {
    String ssoEnable = getConfig("platform.single.user");
    return !(ssoEnable == null || "false".equals(ssoEnable));
  }

  public static boolean enableSingleLogin() {
    String singleLogin = getConfig("platform.single.user");
    return "true".equals(singleLogin) || "1".equals(singleLogin);
  }
}
