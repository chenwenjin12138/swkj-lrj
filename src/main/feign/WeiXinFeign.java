package feign;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author fl
 * @descrip: 微信第三方接口
 * @date 2020/5/19 0019下午 3:01
 */
@Service
public interface WeiXinFeign {

    @PostMapping("/pay/refund")
    String refund(@RequestBody String param);
}
