package com.gzdc.ecar.Model;

import java.util.List;

/**
 * Created by pzn on 2017/1/9 0009.
 */

public class Selectcode {
    /**
     * msg : 查询成功
     * isSuccess : true
     * list : [{"id":"5a682c7d01f545c4a47c8abf61ce24ac","netname":"华北区","netcode":"N000002"},{"id":"806c3f8671f3446280392f24405a76fe","netname":"华南区","netcode":"N000001"}]
     */

    private String msg;
    private boolean isSuccess;
    /**
     * id : 5a682c7d01f545c4a47c8abf61ce24ac
     * netname : 华北区
     * netcode : N000002
     */

    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String id;
        private String netname;
        private String netcode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNetname() {
            return netname;
        }

        public void setNetname(String netname) {
            this.netname = netname;
        }

        public String getNetcode() {
            return netcode;
        }

        public void setNetcode(String netcode) {
            this.netcode = netcode;
        }
    }
}
