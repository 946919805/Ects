package com.gzdc.ecar.Model;

/**
 * Created by pzn on 2017/1/9 0009.
 */

public class Msg {
    /**
     * msg : 注册成功
     * isSuccess : true
     * state : 1
     */

    private String msg;
    private boolean isSuccess;
    private String state;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
