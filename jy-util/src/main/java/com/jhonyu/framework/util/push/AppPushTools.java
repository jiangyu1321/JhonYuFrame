package com.jhonyu.framework.util.push;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;


/**
 * @Description: App推送工具类
 * @className: AppPushTools
 * @userName: jiangyu
 * @date: 2016年2月18日 下午9:58:48
 */
public class AppPushTools
{
    protected static final Logger LOG = LoggerFactory.getLogger(AppPushTools.class);

    public static final String appKey = "668ed4bc83ca0e2b5561e7f7";

    public static final String masterSecret = "11aa22ee7f7642645633bdfd";

    public static JPushClient jpushClient = null;

    /**
     * @Description: 给所有人推送
     * @userName: jiangyu
     * @date: 2016年2月18日 下午9:59:13
     * @param content
     */
    public static void pushAll(String content)
    {
        AppPushTools.jpushClient = new JPushClient(AppPushTools.masterSecret, AppPushTools.appKey);

        Builder build = PushPayload.newBuilder();
        try
        {
            PushPayload payload = build.setPlatform(Platform.android())// 设置接受的平台
            .setAudience(Audience.all())// Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
            .setNotification(Notification.alert(content)).build();
            AppPushTools.jpushClient.sendPush(payload);
        }
        catch (Exception e)
        {
            // TODO: handle exception
            AppPushTools.LOG.error("APP给所有人推送消息失败");
            e.printStackTrace();
        }
    }

    /**
     * @Description: 通过别名推送
     * @userName: jiangyu
     * @date: 2016年2月18日 下午9:59:29
     * @param alias
     *            推送的唯一别名
     * @param content
     *            推送的内容
     */
    public static void pushByAlias(String alias, String content)
    {
        AppPushTools.jpushClient = new JPushClient(AppPushTools.masterSecret, AppPushTools.appKey);
        Builder build = PushPayload.newBuilder();
        try
        {
            PushPayload payload = build.setPlatform(Platform.android())// 设置接受的平台
            .setAudience(Audience.alias(alias))// Audience设置为别名发送
            .setNotification(Notification.alert(content)).build();
            AppPushTools.jpushClient.sendPush(payload);
        }
        catch (Exception e)
        {
            AppPushTools.LOG.error("APP通过别名推送失败，别名：" + alias);
            e.printStackTrace();
        }
    }

    /**
     * @Description: 给多个别名推送，注意：一次不得超过20个别名，否则不允许进行推送
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:00:31
     * @param alias
     *            别名数组
     * @param content
     */
    public static void pushByAliasMult(String[] alias, String content)
    {
        AppPushTools.jpushClient = new JPushClient(AppPushTools.masterSecret, AppPushTools.appKey);
        Builder build = PushPayload.newBuilder();
        try
        {
            if (alias.length <= 20)
            {
                PushPayload payload = build.setPlatform(Platform.android())// 设置接受的平台
                .setAudience(Audience.alias(alias))// Audience设置为别名发送
                .setNotification(Notification.alert(content)).build();
                AppPushTools.jpushClient.sendPush(payload);
            }
        }
        catch (Exception e)
        {
            AppPushTools.LOG.error("APP通过别名推送失败，别名：" + alias);
            e.printStackTrace();
        }
    }

    /**
     * @Description: 标签推送
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:01:01
     * @param tag
     *            推送的标签
     * @param content
     *            推送的内容
     */
    public static void pushByTags(String tag, String content)
    {
        AppPushTools.jpushClient = new JPushClient(AppPushTools.masterSecret, AppPushTools.appKey);
        Builder build = PushPayload.newBuilder();
        try
        {
            PushPayload payload = build.setPlatform(Platform.android())// 设置接受的平台
            .setAudience(Audience.tag(tag))// Audience设置为标签发送
            .setNotification(Notification.alert(content)).build();
            AppPushTools.jpushClient.sendPush(payload);
        }
        catch (Exception e)
        {
            AppPushTools.LOG.error("APP消息推送失败...............");
            e.printStackTrace();
        }
    }
}
