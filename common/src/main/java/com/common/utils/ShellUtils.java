package com.common.utils;

import java.io.IOException;

/**
 * @author Zale
 * @date 2016/11/28
 */
public class ShellUtils {
  /**
   * 执行系统命令
   *
   * @param cmd 命令
   */
  public static void execSystemCmd(String cmd) {
    try {
      Runtime.getRuntime().exec(cmd);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
