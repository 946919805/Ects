package com.gzdc.ecar.Model;

/**
 * Created by pengzhuoneng on 2017/4/25.
 */

public class DriverUpGis {


    /**
     * msg : 报警成功
     * isSuccess : true/fasle
     * id : 报警成功信息对应的id
     */

    private String msg;
    private boolean isSuccess;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
