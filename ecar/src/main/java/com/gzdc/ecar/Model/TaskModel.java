package com.gzdc.ecar.Model;

import java.util.List;

/**
 * Created by pzn on 2017/1/12 0012.
 */

public class TaskModel {


    /**
     * msg : 查询成功
     * isSuccess : true
     * task : [{"id":"6b1a73d862e041549785ece1b1bb1f6c","billtype":"1","bill":"PN000003201704110007","address":[{"loadbeg":"0","bill":"PN000003201704110007","orderbill":"69bfae1ee34c412589ded1844c8e1e82","mainid":"6b1a73d862e041549785ece1b1bb1f6c","linktel":"13287963214","id":"15a1ba5b77c149a4b001c980b903e242","billtype":"1","pokenetname":"","address":null,"gisxy":null,"pokenet":"","clientbill":null,"orderid":"OB000000201703280003","linkman":"王思聪","input_flag":"1","custname":null},{"loadbeg":"0","bill":"PN000003201704110007","orderbill":"b96667a0efde42f881fd4302efd6cd5c","mainid":"6b1a73d862e041549785ece1b1bb1f6c","linktel":"321","id":"c93c488f82f14d638eed5da1a0358ba1","billtype":"1","pokenetname":"","address":"长沙市步步高","gisxy":"112.937778,28.241536","pokenet":"","clientbill":null,"orderid":"OB000000201703290003","linkman":"123","input_flag":"1","custname":null}],"driverend":"0","vehicle":"粤L6666","plannetname":"广州分公司","clientbill":"1704110007","plandate":"2017-04-11 10:23","plannet":"N000003","driverrecv":"0","bookcode":"B000000","driverbeg":"0"}]
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
         * id : 6b1a73d862e041549785ece1b1bb1f6c
         * billtype : 1
         * bill : PN000003201704110007
         * address : [{"loadbeg":"0","bill":"PN000003201704110007","orderbill":"69bfae1ee34c412589ded1844c8e1e82","mainid":"6b1a73d862e041549785ece1b1bb1f6c","linktel":"13287963214","id":"15a1ba5b77c149a4b001c980b903e242","billtype":"1","pokenetname":"","address":null,"gisxy":null,"pokenet":"","clientbill":null,"orderid":"OB000000201703280003","linkman":"王思聪","input_flag":"1","custname":null},{"loadbeg":"0","bill":"PN000003201704110007","orderbill":"b96667a0efde42f881fd4302efd6cd5c","mainid":"6b1a73d862e041549785ece1b1bb1f6c","linktel":"321","id":"c93c488f82f14d638eed5da1a0358ba1","billtype":"1","pokenetname":"","address":"长沙市步步高","gisxy":"112.937778,28.241536","pokenet":"","clientbill":null,"orderid":"OB000000201703290003","linkman":"123","input_flag":"1","custname":null}]
         * driverend : 0
         * vehicle : 粤L6666
         * plannetname : 广州分公司
         * clientbill : 1704110007
         * plandate : 2017-04-11 10:23
         * plannet : N000003
         * driverrecv : 0
         * bookcode : B000000
         * driverbeg : 0
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

        public String getCustname() {
            return custname;
        }

        public void setCustname(String custname) {
            this.custname = custname;
        }

        public String getSendname() {
            return sendname;
        }

        public void setSendname(String sendname) {
            this.sendname = sendname;
        }

        private String custname;
        private String sendname;

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
             * bill : PN000003201704110007
             * orderbill : 69bfae1ee34c412589ded1844c8e1e82
             * mainid : 6b1a73d862e041549785ece1b1bb1f6c
             * linktel : 13287963214
             * id : 15a1ba5b77c149a4b001c980b903e242
             * billtype : 1
             * pokenetname :
             * address : null
             * gisxy : null
             * pokenet :
             * clientbill : null
             * orderid : OB000000201703280003
             * linkman : 王思聪
             * input_flag : 1
             * custname : null
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
            private String address;
            private String gisxy;
            private String pokenet;
            private String clientbill;
            private String orderid;
            private String linkman;
            private String input_flag;
            private String custname;

            public String getFormtype() {
                return formtype;
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

            public String zgetOrderbill() {
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
}