package com.gzdc.ecar.Model;

/**
 * Created by pengzhuoneng on 2017/4/25.
 */

public class RegisterModel {
    /**
     * msg : 注册成功
     * isSuccess : false
     * state : 1
     * id : 23
     */

    private String msg;
    private boolean isSuccess;
    private String state;
    private String id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
