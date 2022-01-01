package com.ghost.springboot.util;

import cn.hutool.core.lang.Snowflake;

/**
 * @program dubbo-demo
 * @description:
 * @author: jackchow
 * @create: 2021/11/28 13:44
 */
public class UnsafetyIdUtil {
    static Snowflake snowflake;

    public UnsafetyIdUtil() {
    }

    public static int[] toCodePoints(CharSequence str) {
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            return new int[0];
        } else {
            String s = str.toString();
            int[] result = new int[s.codePointCount(0, s.length())];
            int index = 0;

            for (int i = 0; i < result.length; ++i) {
                result[i] = s.codePointAt(index);
                index += Character.charCount(result[i]);
            }

            return result;
        }
    }

    public static Long nextId() {
        return snowflake.nextId();
    }

    public static String genUuid() {
        return snowflake.nextId() + "";
    }

    public static Long getTimeFromId(Long id) {
        return (id >> 22) + 1480166465631L;
    }

    static {
        String ip = LocalIpUtil.getLocalIp();
        int[] ints = toCodePoints(ip);
        int sums = 0;
        int[] var3 = ints;
        int var4 = ints.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            int b = var3[var5];
            sums += b;
        }

        int workId = sums % 1024;
        snowflake = new Snowflake(0L, (long) workId);
    }
}

