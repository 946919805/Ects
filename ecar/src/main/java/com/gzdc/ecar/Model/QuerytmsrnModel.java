package com.gzdc.ecar.Model;

import java.util.List;

/**
 * Created by pzn on 2017/1/9 0009.
 */

public class QuerytmsrnModel {
    /**
     * msg : 查询成功
     * isSuccess : true
     * list : [{"id":"D31898C0-BDAC-414F-BCE7-9405E2021D54","custcode":"C000239","custname":"唐山市港口物流有限公司"},{"id":"de1bafae6fea4c8f976af86333b5234a","custcode":"C000269","custname":"中铁快运"},{"id":"e0103627efa84123bc6e4fb81b818ac0","custcode":"C000270","custname":"顺风快递"},{"id":"dcc8a91dc79942c3b4d2ce771ac5bfd5","custcode":"C000271","custname":"圆通快递"},{"id":"6bd188766207439486f805d39d41d2e8","custcode":"C000272","custname":"1"},{"id":"f7b9ef6f7c374e2a8300d0f91db5ffa6","custcode":"C000273","custname":"广东"}]
     */

    private String msg;
    private boolean isSuccess;
    /**
     * id : D31898C0-BDAC-414F-BCE7-9405E2021D54
     * custcode : C000239
     * custname : 唐山市港口物流有限公司
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
        private String custcode;
        private String custname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustcode() {
            return custcode;
        }

        public void setCustcode(String custcode) {
            this.custcode = custcode;
        }

        public String getCustname() {
            return custname;
        }

        public void setCustname(String custname) {
            this.custname = custname;
        }
    }
}
