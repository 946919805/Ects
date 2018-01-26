package com.gzdc.ecar.Model;

import java.util.List;

/**
 * Created by pengzhuoneng on 2017/4/26.
 */

public class CarTastAction_showSendBill2 {
    /**
     * id : 28d6266c284b44abaa4241220c893f69
     * clientbill : 0901060001
     * custname :  华新商贸
     * recvname : 湘潭货速达
     * recvman :
     * recvtel :
     * recvaddr : 湘潭货速达科技有限公司
     * itemlist : [{"id":"0653c32b91314d208dac6705ed278fbc","standard":"1.5L*8支","itemname":"康师傅矿物质水","itemnum":"1","signnum":""},{"id":"29e197ac5cbe4103bfcfbf7b3157aad6","standard":"570ML*15","itemname":"统一ALKAQUA优质饮用矿物质水","itemnum":"1","signnum":""},{"id":"a999fd7a8ea54cb5bc296f1c49e28da4","standard":"330ML*15","itemname":"康师傅矿物质水","itemnum":"1","signnum":""},{"id":"fa12d5de33a9495cb1ebe772352a4f76","standard":"600ml*24","itemname":"统一矿物质水","itemnum":"321","signnum":""}]
     */

    private String id;
    private String clientbill;
    private String custname;
    private String recvname;
    private String recvman;
    private String recvtel;
    private String recvaddr;
    private List<ItemlistBean> itemlist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientbill() {
        return clientbill;
    }

    public void setClientbill(String clientbill) {
        this.clientbill = clientbill;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getRecvname() {
        return recvname;
    }

    public void setRecvname(String recvname) {
        this.recvname = recvname;
    }

    public String getRecvman() {
        return recvman;
    }

    public void setRecvman(String recvman) {
        this.recvman = recvman;
    }

    public String getRecvtel() {
        return recvtel;
    }

    public void setRecvtel(String recvtel) {
        this.recvtel = recvtel;
    }

    public String getRecvaddr() {
        return recvaddr;
    }

    public void setRecvaddr(String recvaddr) {
        this.recvaddr = recvaddr;
    }

    public List<ItemlistBean> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<ItemlistBean> itemlist) {
        this.itemlist = itemlist;
    }

    public static class ItemlistBean {
        /**
         * id : 0653c32b91314d208dac6705ed278fbc
         * standard : 1.5L*8支
         * itemname : 康师傅矿物质水
         * itemnum : 1
         * signnum :
         */

        private String id;
        private String standard;
        private String itemname;
        private String itemnum;
        private String signnum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public String getItemname() {
            return itemname;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public String getItemnum() {
            return itemnum;
        }

        public void setItemnum(String itemnum) {
            this.itemnum = itemnum;
        }

        public String getSignnum() {
            return signnum;
        }

        public void setSignnum(String signnum) {
            this.signnum = signnum;
        }
    }
}
