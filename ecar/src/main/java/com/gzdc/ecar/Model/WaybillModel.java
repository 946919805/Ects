package com.gzdc.ecar.Model;

import java.util.List;

/**
 * Created by pzn on 2017/1/14 0014.
 */

public class WaybillModel {


    /**
     * msg : 查询成功
     * isSuccess : true
     * task : [{"loadbeg":"0","bill":"PN000003201704110026","orderbill":"00bad256056f43adb6022b073ad64cdd","mainid":"fb2162df895e4909b0b79196a1a938e5","linktel":"32134`4","id":"148456681e834b04adc2f770febeb639","billtype":"1","pokenetname":"","address":"湖南省长沙市宁乡县道林镇","gisxy":"undefined","pokenet":"","clientbill":null,"orderid":"OB000000200901080007","linkman":"佛挡杀佛","input_flag":"1","custname":"宁乡县金牛家电有限公司"},{"loadbeg":"0","bill":"PN000003201704110026","orderbill":"435f9f52d0c445ecbf0c31f90b5da6f1","mainid":"fb2162df895e4909b0b79196a1a938e5","linktel":"321","id":"372fd12b253546928d4e6710c6a88388","billtype":"1","pokenetname":"","address":"长沙市步步高","gisxy":"113.316528,23.072152","pokenet":"","clientbill":"0901080006","orderid":"OB000000200901080006","linkman":"123","input_flag":"1","custname":"步步高"},{"loadbeg":"0","bill":"PN000003201704110026","orderbill":"7b72959c19084885a7e3bf94b239be30","mainid":"fb2162df895e4909b0b79196a1a938e5","linktel":"朝阳区","id":"65d9b24f78c348f5ad54855b9f5819ad","billtype":"1","pokenetname":"","address":"艺苑南路13号大院","gisxy":"113.3294,23.099229","pokenet":"","clientbill":"1704110001","orderid":"OB000000201704110001","linkman":"13398273649","input_flag":"1","custname":"王思聪"},{"loadbeg":"0","bill":"PN000003201704110026","orderbill":"61c56cee252d4eb69198664ac917be07","mainid":"fb2162df895e4909b0b79196a1a938e5","linktel":"321","id":"70fb92e1497d463386edbc81a5cd4013","billtype":"1","pokenetname":"","address":"43214321","gisxy":"undefined","pokenet":"","clientbill":null,"orderid":"OB000000200901080002","linkman":"4","input_flag":"1","custname":"中铁快运"},{"loadbeg":"0","bill":"PN000003201704110026","orderbill":"2fda38c073724e7b960606502277308a","mainid":"fb2162df895e4909b0b79196a1a938e5","linktel":"43214321","id":"7f0a83a12df243af814891445445ff63","billtype":"1","pokenetname":"","address":"湖南省长沙市芙蓉区文艺路街道","gisxy":"undefined","pokenet":"","clientbill":null,"orderid":"OB000000200901080008","linkman":"发的啥","input_flag":"1","custname":"湖南华润万家生活超市有限公司"},{"loadbeg":"0","bill":"PN000003201704110026","orderbill":"F00E8C1C-2936-406E-8F59-DDF218F50E14","mainid":"fb2162df895e4909b0b79196a1a938e5","linktel":null,"id":"bc169e9d49e540ed8427d6e315398f74","billtype":"1","pokenetname":"","address":null,"gisxy":"113.045173,23.190573","pokenet":"","clientbill":"1704110001","orderid":"ON000003201704110001","linkman":null,"input_flag":"1","custname":"KIKC"},{"loadbeg":"0","bill":"PN000003201704110026","orderbill":"b8ed18ff54014eb8875236670a106119","mainid":"fb2162df895e4909b0b79196a1a938e5","linktel":"13287963214","id":"fe490618b9c14304bef15f3787a2a8f6","billtype":"1","pokenetname":"","address":null,"gisxy":"113.045173,23.190573","pokenet":"","clientbill":null,"orderid":"OB000000201703280004","linkman":"王思聪","input_flag":"1","custname":"长沙市中转货运仓"}]
     */

    private String msg;
    private boolean isSuccess;
    private List<TaskBean> task;

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

    public List<TaskBean> getTask() {
        return task;
    }

    public void setTask(List<TaskBean> task) {
        this.task = task;
    }

    public static class TaskBean {
        /**
         * loadbeg : 0
         * bill : PN000003201704110026
         * orderbill : 00bad256056f43adb6022b073ad64cdd
         * mainid : fb2162df895e4909b0b79196a1a938e5
         * linktel : 32134`4
         * id : 148456681e834b04adc2f770febeb639
         * billtype : 1
         * pokenetname :
         * address : 湖南省长沙市宁乡县道林镇
         * gisxy : undefined
         * pokenet :
         * clientbill : null
         * orderid : OB000000200901080007
         * linkman : 佛挡杀佛
         * input_flag : 1
         * custname : 宁乡县金牛家电有限公司
         */

        private String loadbeg;
        private String bill;
        private String orderbill;
        private String mainid;
        private String linktel;
        private String id;
        private String billtype;
        private String pokenetname;
        private String address;
        private String gisxy;
        private String pokenet;
        private String clientbill;
        private String orderid;
        private String linkman;
        private String input_flag;
        private String custname;
        private String formtype;
        private String sendname;

        public String getFormtype() {
            return formtype;
        }

        public String getSendname() {
            return sendname;
        }

        public void setSendname(String sendname) {
            this.sendname = sendname;
        }

        public void setFormtype(String formtype) {
            this.formtype = formtype;

        }

        public String getLoadbeg() {
            return loadbeg;
        }

        public void setLoadbeg(String loadbeg) {
            this.loadbeg = loadbeg;
        }

        public String getBill() {
            return bill;
        }

        public void setBill(String bill) {
            this.bill = bill;
        }

        public String getOrderbill() {
            return orderbill;
        }

        public void setOrderbill(String orderbill) {
            this.orderbill = orderbill;
        }

        public String getMainid() {
            return mainid;
        }

        public void setMainid(String mainid) {
            this.mainid = mainid;
        }

        public String getLinktel() {
            return linktel;
        }

        public void setLinktel(String linktel) {
            this.linktel = linktel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBilltype() {
            return billtype;
        }

        public void setBilltype(String billtype) {
            this.billtype = billtype;
        }

        public String getPokenetname() {
            return pokenetname;
        }

        public void setPokenetname(String pokenetname) {
            this.pokenetname = pokenetname;
        }

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

        public String getPokenet() {
            return pokenet;
        }

        public void setPokenet(String pokenet) {
            this.pokenet = pokenet;
        }

        public String getClientbill() {
            return clientbill;
        }

        public void setClientbill(String clientbill) {
            this.clientbill = clientbill;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getInput_flag() {
            return input_flag;
        }

        public void setInput_flag(String input_flag) {
            this.input_flag = input_flag;
        }

        public String getCustname() {
            return custname;
        }

        public void setCustname(String custname) {
            this.custname = custname;
        }
    }
}
