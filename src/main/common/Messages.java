package common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @Description: 配置文件读取
 * @Author Lxh
 * @Date 2020/6/10 17:01
 */
public class Messages {
    private static final String BUNDLE_NAME = "info";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages() {}

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }
}
