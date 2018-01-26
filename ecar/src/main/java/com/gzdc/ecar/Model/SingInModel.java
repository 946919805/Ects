package com.gzdc.ecar.Model;

import java.util.List;

/**
 * Created by pengzhuoneng on 2017/4/27.
 */

public class SingInModel {
    /**
     * msg : 查询成功
     * isSuccess : true
     * task : [{"id":"89cc6061d692477fa5e1750fafa26562","billtype":"4","bill":"PN000003201704130015","address":[{"loadbeg":"0","formtype":"1","bill":"PN000003201704130015","orderbill":"","mainid":"89cc6061d692477fa5e1750fafa26562","linktel":"","id":"1d3b529d1750496a8717e11c0c53ffda","billtype":"4","pokenetname":"","sendname":"普通客户","address":"","gisxy":"113.325985,23.099412","pokenet":"","clientbill":"","orderid":"","linkman":"","input_flag":"4","custname":"唯品会"},{"loadbeg":"0","formtype":"1","bill":"PN000003201704130015","orderbill":"","mainid":"89cc6061d692477fa5e1750fafa26562","linktel":"","id":"5cb2bf2752e94d539de8dfd5b23b7621","billtype":"4","pokenetname":"","sendname":"普通客户","address":"","gisxy":"113.325985,23.099412","pokenet":"","clientbill":"","orderid":"","linkman":"1212","input_flag":"4","custname":"普通客户"},{"loadbeg":"0","formtype":"1","bill":"PN000003201704130015","orderbill":"","mainid":"89cc6061d692477fa5e1750fafa26562","linktel":"","id":"d4ec8bd70f7c4ad39ac87da846c7ce4b","billtype":"4","pokenetname":"","sendname":"普通客户","address":"","gisxy":"113.045173,23.190573","pokenet":"","clientbill":"","orderid":"","linkman":"","input_flag":"4","custname":"普通客户"}],"driverend":"0","vehicle":"粤L6666","plannetname":"","clientbill":"1704130015","plandate":"2017-04-13","plannet":"","driverrecv":"0","bookcode":"B000000","driverbeg":"1"}]
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
         * id : 89cc6061d692477fa5e1750fafa26562
         * billtype : 4
         * bill : PN000003201704130015
         * address : [{"loadbeg":"0","formtype":"1","bill":"PN000003201704130015","orderbill":"","mainid":"89cc6061d692477fa5e1750fafa26562","linktel":"","id":"1d3b529d1750496a8717e11c0c53ffda","billtype":"4","pokenetname":"","sendname":"普通客户","address":"","gisxy":"113.325985,23.099412","pokenet":"","clientbill":"","orderid":"","linkman":"","input_flag":"4","custname":"唯品会"},{"loadbeg":"0","formtype":"1","bill":"PN000003201704130015","orderbill":"","mainid":"89cc6061d692477fa5e1750fafa26562","linktel":"","id":"5cb2bf2752e94d539de8dfd5b23b7621","billtype":"4","pokenetname":"","sendname":"普通客户","address":"","gisxy":"113.325985,23.099412","pokenet":"","clientbill":"","orderid":"","linkman":"1212","input_flag":"4","custname":"普通客户"},{"loadbeg":"0","formtype":"1","bill":"PN000003201704130015","orderbill":"","mainid":"89cc6061d692477fa5e1750fafa26562","linktel":"","id":"d4ec8bd70f7c4ad39ac87da846c7ce4b","billtype":"4","pokenetname":"","sendname":"普通客户","address":"","gisxy":"113.045173,23.190573","pokenet":"","clientbill":"","orderid":"","linkman":"","input_flag":"4","custname":"普通客户"}]
         * driverend : 0
         * vehicle : 粤L6666
         * plannetname :
         * clientbill : 1704130015
         * plandate : 2017-04-13
         * plannet :
         * driverrecv : 0
         * bookcode : B000000
         * driverbeg : 1
         */

        private String id;
        private String billtype;
        private String bill;
        private String driverend;
        private String vehicle;
        private String plannetname;
        private String clientbill;
        private String plandate;
        private String plannet;
        private String driverrecv;
        private String bookcode;
        private String driverbeg;
        private List<AddressBean> address;

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

        public String getBill() {
            return bill;
        }

        public void setBill(String bill) {
            this.bill = bill;
        }

        public String getDriverend() {
            return driverend;
        }

        public void setDriverend(String driverend) {
            this.driverend = driverend;
        }

        public String getVehicle() {
            return vehicle;
        }

        public void setVehicle(String vehicle) {
            this.vehicle = vehicle;
        }

        public String getPlannetname() {
            return plannetname;
        }

        public void setPlannetname(String plannetname) {
            this.plannetname = plannetname;
        }

        public String getClientbill() {
            return clientbill;
        }

        public void setClientbill(String clientbill) {
            this.clientbill = clientbill;
        }

        public String getPlandate() {
            return plandate;
        }

        public void setPlandate(String plandate) {
            this.plandate = plandate;
        }

        public String getPlannet() {
            return plannet;
        }

        public void setPlannet(String plannet) {
            this.plannet = plannet;
        }

        public String getDriverrecv() {
            return driverrecv;
        }

        public void setDriverrecv(String driverrecv) {
            this.driverrecv = driverrecv;
        }

        public String getBookcode() {
            return bookcode;
        }

        public void setBookcode(String bookcode) {
            this.bookcode = bookcode;
        }

        public String getDriverbeg() {
            return driverbeg;
        }

        public void setDriverbeg(String driverbeg) {
            this.driverbeg = driverbeg;
        }

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public static class AddressBean {
            /**
             * loadbeg : 0
             * formtype : 1
             * bill : PN000003201704130015
             * orderbill :
             * mainid : 89cc6061d692477fa5e1750fafa26562
             * linktel :
             * id : 1d3b529d1750496a8717e11c0c53ffda
             * billtype : 4
             * pokenetname :
             * sendname : 普通客户
             * address :
             * gisxy : 113.325985,23.099412
             * pokenet :
             * clientbill :
             * orderid :
             * linkman :
             * input_flag : 4
             * custname : 唯品会
             */

            private String loadbeg;
            private String formtype;
            private String bill;
            private String orderbill;
            private String mainid;
            private String linktel;
            private String id;
            private String billtype;
            private String pokenetname;
            private String sendname;
            private String address;
            private String gisxy;
            private String pokenet;
            private String clientbill;
            private String orderid;
            private String linkman;
            private String input_flag;
            private String custname;

            public String getLoadbeg() {
                return loadbeg;
            }

            public void setLoadbeg(String loadbeg) {
                this.loadbeg = loadbeg;
            }

            public String getFormtype() {
                return formtype;
            }

            public void setFormtype(String formtype) {
                this.formtype = formtype;
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

            public String getSendname() {
                return sendname;
            }

            public void setSendname(String sendname) {
                this.sendname = sendname;
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
}
