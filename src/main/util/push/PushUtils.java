package util.push;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

@SuppressWarnings("deprecation")
public class PushUtils {

    private static final String APP_KEY = "eafffd68d5a30b76ea8a2ebf";
    private static final String MASTER_SECRET = "2685296eb76434d50283c476";
    private static final String APP_KEY_ENTERPRISE = "ad4dec9546c945599d1ab00e";
    private static final String MASTER_SECRET_ENTERPRISE = "803d88ce2c564e1fba190a08";
    private static final String IOS_APP_KEY = "0ca5feaa9c58cb2db8c98b8d";
    private static final String IOS_MASTER_SECRET = "8c5f7f766cead36e3894da95";
    public static JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, 3);
    public static JPushClient jpushClientEnterprise = new JPushClient(MASTER_SECRET_ENTERPRISE, APP_KEY_ENTERPRISE, 3);
    public static JPushClient IOSjpushClient = new JPushClient(IOS_MASTER_SECRET, IOS_APP_KEY, 3);

    /**
     * 
     * @Title: SendPushAll
     * @Description: 推送所有用户
     * @param alert
     *            消息内容
     * @throws Exception
     * @author zhaoyubo
     * @date: 2015-12-9
     * @lastModifyTime 2015-12-9
     */
    public static void SendPushAll(String alert) throws Exception {

        // 生成推送内容
        PushPayload payload = buildPushObject_all_alias_alertIos(alert);
        jpushClient.sendPush(payload);
    }

    /**
     * 
     * @Title: SendPushAll
     * @Description: 推送所有用户
     * @param title
     *            标题
     * @param alert
     *            内容
     * @throws Exception
     * @author zhaoyubo
     * @date: 2015-12-9
     * @lastModifyTime 2015-12-9
     */
    public static void SendPushAll(String title, String alert) throws Exception {

        // 生成推送内容
        PushPayload payload = buildPushObject_android_tag_alertWithTitle(title, alert);
        // 生成推送内容
        PushPayload IOSpayload = buildPushObject_all_alias_alertIos(alert);
        jpushClient.sendPush(payload);
        IOSjpushClient.sendPush(IOSpayload);
    }

    /**
     * 
     * @Title: SendPushByTag
     * @Description: 招标签推送用户
     * @param title
     * @param alert
     * @param tag
     * @throws Exception
     * @author zhaoyubo
     * @date: 2016-1-18
     * @lastModifyTime 2016-1-18
     */
    public static void SendPushByTag(String title, String alert, String tag) throws Exception {

        PushPayload payload = buildPush_android_ByTag(title, alert, tag);
        jpushClientEnterprise.sendPush(payload);
    }

    /**
     * <b>buildPushObject_all_alias_alertIos</b>：(IOS生产环境推送)<br>
     * >
     * @param<br>
     * @param<br>
     * @return PushPayload<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public static PushPayload buildPushObject_all_alias_alertIos(String alert) {

        return PushPayload.newBuilder().setPlatform(Platform.ios())// 设置接受的平台
        .setAudience(Audience.all())// Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
        .setOptions(Options.newBuilder().setApnsProduction(true).build())// 设置为生产环境
        .setNotification(Notification.alert(alert)).build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle(String title, String alert) {

        return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.all()).setNotification(Notification.android(alert, title, null)).build();
    }

    public static PushPayload buildPush_android_ByTag(String title, String alert, String tag) {

        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.tag(tag)).setNotification(Notification.android(alert, title, null)).build();
    }

}
