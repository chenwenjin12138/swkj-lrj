package config;

import lombok.Getter;

/**
 * @author : cwj
 * @describe : 商户在微信支付平台的参数
 * @date : 2020-4-10
 */
public class PayConfig {
    // 这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
    // 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
    // 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改
    /*****************************小程序提交参数***********************************/
    public static final String MIN_KEY = "2rSy2j3XMsCebEDa9hUZKuPi1UWbxm5R";
    // 微信分配的公众号ID（开通公众号之后可以获取到）
    public static final String APP_ID = "wxa560d266e8d31842";
    // 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
    public static final String MIN_MCH_ID = "1325631701";
    // 接口密钥
    private static String min_APPSECRET = "10147ef8bb9fbc9e344b75c857f0a2e9";
    private static String min_APPSECRET_2 = "5f58b4f94f94f437e982b486a265ea58";
    // 受理模式下给子商户分配的子商户号
    private static String min_subMchID = "";
    // HTTPS证书的本地路径
    private static String min_certLocalPath = "";
    // HTTPS证书密码，默认密码等于商户号MCHID
    private static String min_certPassword = "1325631701";
    // 是否使用异步线程的方式来上报API测速，默认为异步模式
    private static boolean min_useThreadToDoReport = true;
    // 机器IP
    private static String min_ip = "";
    // noncestr
    private static String min_noncestr = "123456789";

    /******************APP支付参数*******************************************/
    public static final String APP_KEY = "axuyPyL2tEKEnvArzAvhpA9BuridrGjc";
    //微信分配的公众号ID（开通公众号之后可以获取到）
    private static String app_appID = "wxcbdfe89e9cef31d9";
    //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
    private static String app_mchID = "1300635601";
    //受理模式下给子商户分配的子商户号
    private static String app_subMchID = "";
    //HTTPS证书的本地路径
    private static String app_certLocalPath = "";
    //HTTPS证书密码，默认密码等于商户号MCHID
    private static String app_certPassword = "";
    //是否使用异步线程的方式来上报API测速，默认为异步模式
    private static boolean app_useThreadToDoReport = true;
    //机器IP
    private static String app_ip = "";

    //以下是几个API的公共路径：
    /** 统一下单接口 **/
    public static String UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /** 获取成员信息 **/
    public static String GETUSERINFOR = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
    /** 获取网页授权token **/
    public static String GETTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /** access_token  API **/
    public static String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    // 1）被扫支付API
    public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";
    // 2）被扫支付查询API
    public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";
    // 3）退款API
    public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    // 4）退款查询API
    public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";
    // 5）撤销API
    public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";
    // 6）下载对账单API
    public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";
    // 7) 统计上报API
    public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";
    // 8) JS-SDK签名ticket_API
    public static String TICKET_API = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";


    /******************支付宝 ：支付参数*******************************************/
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_URL= "https://openapi.alipay.com/gateway.do";
    private static final String ALIPAY_APP_ID = "2021001162600517";
    private static final String ALIPAY_PID = "2088121460107602"; //商户Id
    //商户私钥
    private static final String ALIPAY_APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCi0+24m0ilqVdJyNBH6IIKuig5qK6maZ2GefIZfTHhCewBojWFVnXYDQq0woYiE/nY8JIFrhj1JrmtqdXsrJ4Gpz2nB3iGGGiS2hSxd1jNUippKy/hCByLdyL0YeP53HHn3QfwsbMoGOjSTNEm01HZyQIQ4YDf0dS7NerV9tiXlI0snrnINQryGgHIB1TDNXppmVJlLIM49HhfA/cMAKaPDxKqOkehXb2eT9f/+FPnXPyYiGlixHNZNUkfIJEaNcsb/aXnsXQyaICLMEZt4d+M/ZdRHBsz9PoxeL3Z+VvUTTyrOxeObxQbrHxrfVG1fUQSXmVhOMhYrzBPzIfAy7W7AgMBAAECggEAWbQZre9WiXWOkiagoEz2f4RpLsLAXQuobfJ2rxYqyYiWzZuYkrm8OGeO82CLVvDcX2jBeYGdXEmWV5dONJIwiQfPzlouSundirG8XryaXpsapE/Xk8jNOQRMbIgVeYy57/v0KOUtBzUZ5unM++/Fkw7LJZOmVSgbY3vjwTa5wl+Cd1lWfov46X0u8rf0NG/IipgKAcdM4yP6ThD3MR4SPTmpordvZBlsv90XrypgprtYtscT/ESy5gVp3p5YJJVmrsTuj0mmHwWhlSxkqpalR7gOMrLsalo2PHL+lORUUBTgw5H9EEAICR+cN7FXLTDc2rEYax+t2RFBjnF8HuzV4QKBgQDUhxc6maV9KIfQ3UbloTrYrpSzNkxe4N/RysajY5MNe/A9fGUMxxvzJWS7wiFN1eLVTREiKRriPk0JMcZZssIPmbhVWRu4IpSwlyBiyLs4shsQleLziES7NGN9LAYwuR7cnCj3NWs4K27fWgvpTnci6Ryjdvqoya+3bBANRvIBKwKBgQDEIlRP5Yh5b0CiQ+vxCKNOYT3zZb8IMvL0LSn/Fvrxcp10YodMV0M5aDxZPRvzefRlfb0SLeT9k5WKZdE7IlobEo6BwYOUiPZJ7nJVe2vtHIwGDIHRH3ayKS8Rle2Z13O2sZQBHqkAFZh3yNVBle1G+kMIcST+t66dmVCWIDE1sQKBgQCibpFxZ0cVTmnnV4e9L6oTO/NIuWJLTaGi7VE+RXd54dTumWl+B5u0DUtdOXVM0QEqN1m1+yah7i3grhtEKRyq4bkB+jX2WO+9u/OzrGlzXqTS20v3B5pIXjT/0Sr2CDavM+cXcct9xaxapq7d5OunUfVidigD4woarUvjaerCuwKBgQCrJFFxjhYx8DPz41Sj9CeboxqNokC5BMwe6LH2lrTNrndaMQdiQ9qy4xOVws76+3WYgclTbZyc8ATSyNlzwhvh96VF7/fCymguRtSZZqLcOcatGIjGU7Ac8fbSX4L+dJPR3M4K1BgfrLhn/WSoYAy1nHaVjTT81oaVmkxJUzndYQKBgFoKPxoVt9iOexAmk5OlcGPC1+tuUe8Orpl2uY+jCz5cfQ1F6D60fGQpMtiQ1Gq/4XVswl4I/X1nQgee3YJUeedHIdXbR5gsyC5qX2eJIQOR2AHU4eUFYx1ZyQNL3rtpjiK3OJpDPWUhOAUzZPxiLAQ+qF3wyoSTdqKyUTHdmOGk";
    //支付宝公钥
    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhC7lq3sHekPnBFmdzqB+S/ZwJnP1iJsXSkzo8dgoZ7mnrzby4AWs3ZZwOSgFRIrAnRCXMj2qt1POd6kC2lBHg0wMDQKS+dcwlgRX6G8wtLvwpq1OO4NeeG/31V/aEiqaVRmutL17MArW+FVK0FXpLO3DkZ5N9frxfZyeejKtR+2cynJI70eLhNFb8XERLQPvCxcGLswMILnCUFbJjOgQGkUVTPIkdDxHSiqgyobp3oMoeNoAM9kYWYaqKRPx7IZRdXbjEQrlXCs6NuZGdY4ACp7uDppY2bOj6yw2d3wDbA0AlVa12LleQnKT2F63oTQOnX8mHUHh5A8mKs0yDSe7VwIDAQAB";

    private static final String ALIPAY_FORMAT = "json";
    private static final String ALIPAY_CHARSET = "UTF-8";
    private static final String ALIPAY_SIGN_TYPE = "RSA2";


}
