package vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/6/30 14:44
 */
@Data
@NoArgsConstructor
public class QRCodeAPI {
    public static String URL = "http://lxh.ngrok2.xiaomiqiu.cn/getQRCode";

    public static String PATH = "D://wy/pic/";

}
