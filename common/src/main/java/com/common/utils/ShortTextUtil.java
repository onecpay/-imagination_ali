package com.common.utils;/**
 * @Auther: hebing
 * @Date: 2018/11/19 09:46
 * @Description:
 */

import java.util.UUID;

/**
 * @author: jiabing
 * @Date: 2018/11/19 09:46
 * @Description:
 */
public class ShortTextUtil {

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(get12Char(uuid));
    }

    /**
     * 获取短字符
     * @param uuid
     * @return 大写
     */
    public static String get12Char(String uuid) {
        String[] arr = ShortText(uuid, "");
        String rst = (arr[0] + arr[1]).toUpperCase();
        return rst.substring(0, 4) + "-" + rst.substring(4, 8) + "-"
                + rst.substring(8, 12);
    }

    /**
     * 获取短字符
     * @param uuid
     * @return 大写
     */
    public static String get12Char(String uuid, String key) {
        String[] arr = ShortText(uuid, key);
        String rst = (arr[0] + arr[1]).toUpperCase();
        return rst;
    }

    private static String[] ShortText(String string, String key) {
        // 要使用生成URL的字符
        String[] chars = new String[]{
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B",
                "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String hex = Md5Util.MD5(key + string);
        int hexLen = hex.length();
        int subHexLen = hexLen / 8;
        String[] ShortStr = new String[4];

        for(int i = 0; i < subHexLen; i++) {
            String outChars = "";
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

            for(int k = 0; k < 6; k++) {
                int index = (int) (Long.valueOf("0000003D", 16) & idx);
                outChars += chars[index];
                idx = idx >> 5;
            }
            ShortStr[i] = outChars;
        }

        return ShortStr;
    }
}
