package com.newcapec.wx;

/**
 * @author guoxianwei 2017-05-27 16:33:21
 */
public class WxConstants {

    public static final String APP_ID = "wx522a11f1ea0a4fc5";

    public static final String APP_SECRET = "a4345fe9d5fa674ffe449a9655fdb858";

    public static final String ACCESS_TOKEN_KEY = "weixin";

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

    public static final String WX_MENU_CREATION_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={0}";

    public static final String WX_MENU_QUERY_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token={0}";

    public static final String WX_MENU_REMOVE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token={0}";

    public static final String WX_MESSAGE_TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}";

    public static String getAccessTokenRedisKey(String appid) {
        return ACCESS_TOKEN_KEY + appid;
    }

}
