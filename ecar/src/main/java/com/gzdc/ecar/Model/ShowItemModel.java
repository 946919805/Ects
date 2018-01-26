package com.gzdc.ecar.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pzn on 2017/1/11 0011.
 */

public class ShowItemModel {
    /**
     * msg : 查询成功
     * isSuccess : true
     * list1 : [{"address":"广州东站1","gisxy":"113.331234,23.154947","linkman":"13213","linktel":"213213213"}]
     * list2 : [{"sendgis":"113.197003,23.143181","linkman":null,"linktel":null,"sendaddr":"广州市白云路27号交通大厦"}]
     */

    private String msg;
    private boolean isSuccess;
    /**
     * address : 广州东站1
     * gisxy : 113.331234,23.154947
     * linkman : 13213
     * linktel : 213213213
     */

    private List<List1Bean> list1;
    /**
     * sendgis : 113.197003,23.143181
     * linkman : null
     * linktel : null
     * sendaddr : 广州市白云路27号交通大厦
     */

    private List<List2Bean> list2;

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

    public List<List1Bean> getList1() {
        return list1;
    }

    public void setList1(List<List1Bean> list1) {
        this.list1 = list1;
    }

    public List<List2Bean> getList2() {
        return list2;
    }

    public void setList2(List<List2Bean> list2) {
        this.list2 = list2;
    }

    public static class List1Bean implements Serializable {
        private String address;
        private String gisxy;
        private String linkman;
        private String linktel;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGisxy() {
            return gisxy;
        }

        public void setGisxy(String gisxy) {
            this.gisxy = gisxy;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getLinktel() {
            return linktel;
        }

        public void setLinktel(String linktel) {
            this.linktel = linktel;
        }
    }

    public static class List2Bean {
        private String sendgis;
        private Object linkman;
        private Object linktel;
        private String sendaddr;

        public String getSendgis() {
            return sendgis;
        }

        public void setSendgis(String sendgis) {
            this.sendgis = sendgis;
        }

        public Object getLinkman() {
            return linkman;
        }

        public void setLinkman(Object linkman) {
            this.linkman = linkman;
        }

        public Object getLinktel() {
            return linktel;
        }

        public void setLinktel(Object linktel) {
            this.linktel = linktel;
        }

        public String getSendaddr() {
            return sendaddr;
        }

        public void setSendaddr(String sendaddr) {
            this.sendaddr = sendaddr;
        }
    }
}
