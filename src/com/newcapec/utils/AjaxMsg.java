package com.newcapec.utils;

/**
 * 向后台extjs传递信息
 *
 * @author guoxianwei 2015-07-10 14:20
 */
public class AjaxMsg {

    private boolean success;
    private String msg;

    public AjaxMsg() {
        this.success = true;
    }

    public AjaxMsg(String message) {
        this();
        this.msg = message;
    }

    public AjaxMsg(boolean success, String message) {
        this.success = success;
        this.msg = message;
    }

    public static AjaxMsg success() {
        return new AjaxMsg();
    }

    public static AjaxMsg success(String message) {
        return new AjaxMsg(message);
    }

    public static AjaxMsg fail(String message) {
        return new AjaxMsg(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String message) {
        this.msg = message;
    }
}
