package util;

import java.util.UUID;

/**
 * @author Lxh
 * @date 2020/3/26 14:47
 */
public class RandomUtil {

    private static final String[] strArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b",
            "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    public static String getUUID() {

        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
                + s.substring(24);
    }
}
