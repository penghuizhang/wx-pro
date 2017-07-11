package com.newcapec.wx.utils;

import com.newcapec.global.utils.GsonUtil;
import com.newcapec.global.utils.HttpClientUtil;
import com.newcapec.global.wx.model.AccessTokenResult;
import com.newcapec.global.wx.model.ErrorInfoResult;
import com.newcapec.global.wx.model.UserInfoResult;
import com.newcapec.wx.WxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @author guoxianwei 2017-05-27 15:57:21
 */
public class WxUtil {

    private static final Logger logger = LoggerFactory.getLogger(WxUtil.class);

    /**
     * 获取access token
     *
     * @param appid     应用ID
     * @param appsecret 密钥
     * @return access token
     */
    public static String getAccessToken(String appid, String appsecret) {
        String result = "";
        try {
            Map<String, Object> response = HttpClientUtil.doHttpGet(MessageFormat.format(WxConstants.ACCESS_TOKEN_URL, appid, appsecret));
            if (response.get("errcode") != null && !"ok".equals(response.get("errmsg"))) {
                logger.debug("调用{getAccessToken}发生错误，错误信息为：" + response.get("errcode") + "," + response.get("errmsg"));
            } else {
                result = response.get("access_token").toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建菜单
     *
     * @param appid       应用ID
     * @param menuJsonStr 菜单JSON字符串
     */
    public static void createMenu(String appid, String menuJsonStr) {
        //String accessToken = RedisUtil.get(WxConstants.getAccessTokenRedisKey(appid));
        String accessToken = getAccessToken(WxConstants.APP_ID, WxConstants.APP_SECRET);
        try {
            Map<String, Object> response = HttpClientUtil.doHttpPost(MessageFormat.format(WxConstants.WX_MENU_CREATION_URL, accessToken), menuJsonStr);
            if (response.get("errcode") != null && !"ok".equals(response.get("errmsg"))) {
                logger.debug("调用{getAccessToken}发生错误，错误信息为：" + response.get("errcode") + "," + response.get("errmsg"));
            } else {
                logger.debug("菜单创建接口调用成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("调用{createMenu}发生错误，错误信息为：" + e.getMessage());
        }
    }

    public static Map<String, Object> queryMenu(String appid) {
        //String accessToken = RedisUtil.get(WxConstants.getAccessTokenRedisKey(appid));
        String accessToken = getAccessToken(WxConstants.APP_ID, WxConstants.APP_SECRET);
        Map<String, Object> response = null;
        try {
            response = HttpClientUtil.doHttpGet(MessageFormat.format(WxConstants.WX_MENU_QUERY_URL, accessToken));
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("调用{createMenu}发生错误，错误信息为：" + e.getMessage());
        }
        return response;
    }

    /**
     * 删除菜单
     *
     * @param appid 应用ID
     */
    public static void removeMenu(String appid) {
        //String accessToken = RedisUtil.get(WxConstants.getAccessTokenRedisKey(appid));
        String accessToken = getAccessToken(WxConstants.APP_ID, WxConstants.APP_SECRET);
        try {
            Map<String, Object> response = HttpClientUtil.doHttpGet(MessageFormat.format(WxConstants.WX_MENU_REMOVE_URL, accessToken));
            if (response.get("errcode") != null && !"ok".equals(response.get("errmsg"))) {
                logger.debug("调用{getAccessToken}发生错误，错误信息为：" + response.get("errcode") + "," + response.get("errmsg"));
            } else {
                logger.debug("菜单删除接口调用成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("调用{removeMenu}发生错误，错误信息为：" + e.getMessage());
        }
    }

    /**
     * 生成授权URL
     *
     * @param callbackUrl 回调URL
     * @return 回调URL
     */
    public static String authorizeUrl(String callbackUrl,String state) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").
                    append(WxConstants.APP_ID).
                    append("&redirect_uri=").
                    append(URLEncoder.encode(callbackUrl, "UTF-8")).
                    append("&response_type=code").
                    append("&scope=snsapi_userinfo").
                    append("&state=STATE").
                    append("#wechat_redirect");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * 生成授权URL
     *
     * @param callbackUrl 回调URL
     * @return 回调URL
     */
    public static String authorizeBaseUrl(String callbackUrl,String state) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").
                    append(WxConstants.APP_ID).
                    append("&redirect_uri=").
                    append(URLEncoder.encode(callbackUrl, "UTF-8")).
                    append("&response_type=code").
                    append("&scope=snsapi_base").
                    append("&state="+state).
                    append("#wechat_redirect");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
    /**
     * 获取access_token
     *
     * @param code
     * @return
     */
    public static AccessTokenResult accessToken(String code) {
        AccessTokenResult accessTokenResult = null;
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=").
                append(WxConstants.APP_ID).
                append("&secret=").
                append(WxConstants.APP_SECRET).
                append("&code=").
                append(code).
                append("&grant_type=authorization_code");
        String result = HttpClientUtil.doHttpGetForString(sb.toString());
        if (result != null) {
            accessTokenResult = GsonUtil.fromJsonToBean(result, AccessTokenResult.class);
        }
        return accessTokenResult;
    }

    /**
     * refreshToken 重新获取 accessToken
     *
     * @param refreshToken
     * @return
     */
    public static AccessTokenResult refreshToken(String refreshToken) {
        AccessTokenResult accessTokenResult = null;
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=").
                append(WxConstants.APP_ID).
                append("&refresh_token=").
                append(refreshToken).
                append("&grant_type=refresh_token");
        String result = HttpClientUtil.doHttpGetForString(sb.toString());
        if (result != null) {
            accessTokenResult = GsonUtil.fromJsonToBean(result, AccessTokenResult.class);
        }
        return accessTokenResult;
    }

    /**
     * 获取用户信息 需要snstype 为 userinfo 非 base
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public static UserInfoResult userInfo(String accessToken, String openId) {
        UserInfoResult userInfoResult = null;
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/userinfo?access_token=").
                append(accessToken).
                append("&openid=").
                append(openId).
                append("&lang=zh_CN");
        String result = HttpClientUtil.doHttpGetForString(sb.toString());
        if (result != null) {
            userInfoResult = GsonUtil.fromJsonToBean(result, UserInfoResult.class);
        }
        return userInfoResult;
    }

    /**
     * 判断accesstoken 是否有效
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public static boolean authAccessToken(String accessToken, String openId) {
        ErrorInfoResult errorInfoResult = null;
        boolean isValid = false;
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/auth?access_token=").
                append(accessToken).
                append("&openid=").
                append(openId);
        String result = HttpClientUtil.doHttpGetForString(sb.toString());
        if (result != null) {
            errorInfoResult = GsonUtil.fromJsonToBean(result, ErrorInfoResult.class);
            if (0 == errorInfoResult.getErrcode()) {
                isValid = true;
            }
        }
        return isValid;
    }


    /**
     * 模板消息发送
     *
     * @param appid       应用ID
     * @param msgJsonStr  消息JSON字符串
     */
    public static void templateMsgSend(String appid, String msgJsonStr) {
        //String accessToken = RedisUtil.get(WxConstants.getAccessTokenRedisKey(appid));
        String accessToken = getAccessToken(WxConstants.APP_ID, WxConstants.APP_SECRET);
        try {
            Map<String, Object> response = HttpClientUtil.doHttpPost(MessageFormat.format(WxConstants.WX_MESSAGE_TEMPLATE_SEND_URL, accessToken), msgJsonStr);
            if (response.get("errcode") != null && !"ok".equals(response.get("errmsg"))) {
                logger.debug("调用{getAccessToken}发生错误，错误信息为：" + response.get("errcode") + "," + response.get("errmsg"));
            } else {
                logger.debug("模板消息发送接口调用成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("调用{templateMsgSend}发生错误，错误信息为：" + e.getMessage());
        }
    }


    public static void main(String[] args) {
       // System.out.println(getAccessToken("wx31484932b9044576", "ef2d0581b3434722af9a770d6ae0e527"));

//        templateMsgSend("", TemplateMsg.getTestTemplate());
    }
}
