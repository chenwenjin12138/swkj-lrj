package common;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lxh
 * @date 2020/3/23 9:52
 */
public class Constant {
    /**
     * <!>注意<!><!>注意<!><!>注意<!>
     * <!>注意<!><!>注意<!><!>注意<!>
     * <!>注意<!><!>注意<!><!>注意<!>
     * 数字类型如整型,不能使用Integer来定义,推荐int
     * 因为java会自动拆箱,如果用Integer对象类型定义静态常量,你所比较的也是个Integer类型用
     * ==比较就会不相等,因为是两个内存地址不同的对象。
     * <!>注意<!><!>注意<!><!>注意<!>
     * <!>注意<!><!>注意<!><!>注意<!>
     * <!>注意<!><!>注意<!><!>注意<!>
     *
     */
    /**
     * 1:代表该用户或者角色等为可用状态.
     */
    public static final int ACTIVE = 1;
    /**
     * 0:代表该用户或者角色等为不可用可用状态.
     */
    public static final int FORBIDDEN = 0;
    /**
     * 0:代表请求执行后返回客户端状态为执行成功.
     */
    public static final int SUCCESS = 0;
    /**
     * 1:代表请求执行后返回客户端状态为执行失败.
     */
    public static final int FAIL = 1;
    /**
     * 1:代表商品为显示状态.
     */
    public static final int SHOW = 1;
    /**
     * 0:代表商品为显示隐藏状态.
     */
    public static final int HIDDEN = 0;
    /**
     * 该变量为默认网页后台管理用户密码.
     */
    /**
     * 订单追踪状态用户设定类型
     */
    public static final int TRACESTATUSUSERDIY = 2;
    /** 默认密码 **/
    public static final String DEFAULTPASSWORDS = "123456";
    /** 订单未完成 **/
    public static final int UNFINISHED = 1;
    /** 订单已完成 **/
    public static final int FINISHED = 2;
    /** 订单未支付 **/
    public static final int NONPAYMENT = 1;
    /** 订单已支付 **/
    public static final int PAYMENT = 2;
    /** 订单支付失败 **/
    public static final int PAYMENTFAILED = 3;
    /** 订单未抢 **/
    public static final int UNLOCK = 1;
    /** 订单已抢 **/
    public static final int LOCK = 2;
    /** 订单退款 **/
    public static final int REFUND = 1;
    /**
     * 上传图片类型
     */
    /** 商品图片 **/
    public static final int ITEMIMG = 0;
    /** 头像 **/
    public static final int HEADPHOTO = 1;
    /** 轮播图片 **/
    public static final int BANNERIMG = 2;
    /** APP客户端用户 **/
    public static final int APPUSER = 1;
    /** APP企业端用户 **/
    public static final int STAFFUSER = 2;
    /** 后台管理用户 **/
    public static final int ADMIN = 3;
    /** 未读 **/
    public static final int NOREAD = 1;
    /** 已读 **/
    public static final int READ = 2;
    /** 关于我们 **/
    public static final int ABOUTUS = 1;
    /** 帮助信息 **/
    public static final int HELP = 2;
    /** 法律声明 **/
    public static final int LAW = 3;
    /** 商户用户 **/
    public static final int BUSINESSTYPE=1;
    /** 新增**/
    public static final int ADD=1;
    /** 更新 **/
    public static final int EDIT=2;
    /**商户权限**/
    public static final int BUSINESSROLEID=4;
    /** 空结果 **/
    private static Map<String, Object> MAP;

    /** 单例MAP对象 **/
    /** 空对象 **/
    public synchronized static Map<String, Object> NULL() {

        if (MAP != null) {
            return MAP;
        }
        else {
            MAP = new HashMap<String, Object>();
            return MAP;
        }
    }

    /**
     * 已删除
     */
    public static final int DELETED = 1;

    /**
     * 未删除
     */
    public static final  int NOT_DELETED = 0;

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


}
