package service;

import config.PayConfig;
import feign.WeiXinFeign;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pojo.PayOperation;
import pojo.pay.WeiXinPay;
import util.WXPayUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static util.ClassMapUtil.setConditionMap;

/**
 * @author fl
 * @descrip: 微信第三方接口
 * @date 2020/5/19 0019上午 10:49
 */
@Service
public class WeiXinService {


    /**
     *
     * @param payOperation
     * @return
     * @throws Exception
     */
    public String doRefund(PayOperation payOperation) throws Exception {
        WeiXinPay weiXinPay = new WeiXinPay();
        weiXinPay.setAppid(PayConfig.APP_ID);
        weiXinPay.setSign("");
        weiXinPay.setMch_id(PayConfig.MIN_MCH_ID);
        weiXinPay.setNonce_str(String.valueOf(new Date().getTime()));
        weiXinPay.setOut_refund_no(payOperation.getOutTradeNo());
        weiXinPay.setRefund_fee(payOperation.getTotalFee());
        weiXinPay.setTotal_fee(payOperation.getTotalFee());
        weiXinPay.setTransaction_id(payOperation.getTransactionId());
        Map<String,Object> map = setConditionMap(weiXinPay);
        weiXinPay.setSign(WXPayUtil.getSign(map,PayConfig.APP_KEY));
        map =  setConditionMap(weiXinPay);
        //将请求参数转化为微信支付要求的xml格式文件
        String xml = WXPayUtil.mapToXml1(map);
        System.out.println(xml);
       /* String result = weiXinFeign.refund(xml);
        System.out.println("返回结果："+result);
        return result;*/
        return null;
    }


}
