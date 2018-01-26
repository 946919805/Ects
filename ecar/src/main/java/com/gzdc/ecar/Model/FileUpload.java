package com.gzdc.ecar.Model;

/**
 * Created by pengzhuoneng on 2017/4/25.
 */

public class FileUpload {

    /**
     * msg : 上传成功
     * isSuccess : true
     * file : 上传到服务器上的文件的全路径文件
     */

    private String msg;
    private boolean isSuccess;
    private String file;

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
