package com.gzdc.ecar.Model;

/**
 * Created by pengzhuoneng on 2017/3/27.
 */

public class User_modle {


    /**
     * isSuccess : true
     * msg : 登录成功
     * userInfo : {"password":"C4CA4238A0B923820DCC509A6F75849B","userid":"d4cff93e11904f26babc3f8547324a75","cityname":"","citycode":"","custid":"","regtype":"","state":"1","user_name":"祥兴","user_code":"123","adminid":"","headimgurl":"","netname":"","phoneno":"8502996","netcode":"","vehicle":"粤L6666","usertype":"D","nettype":"","cmpname":"","custname":"123","custcode":"C000276","netid":"","wxid":"","areaname":"","cityarea":"","nettree":"","bookname":"平台帐套","dept_id":"0000","bookcode":"B000000"}
     */

    private boolean isSuccess;
    private String msg;
    private UserInfoBean userInfo;

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * password : C4CA4238A0B923820DCC509A6F75849B
         * userid : d4cff93e11904f26babc3f8547324a75
         * cityname :
         * citycode :
         * custid :
         * regtype :
         * state : 1
         * user_name : 祥兴
         * user_code : 123
         * adminid :
         * headimgurl :
         * netname :
         * phoneno : 8502996
         * netcode :
         * vehicle : 粤L6666
         * usertype : D
         * nettype :
         * cmpname :
         * custname : 123
         * custcode : C000276
         * netid :
         * wxid :
         * areaname :
         * cityarea :
         * nettree :
         * bookname : 平台帐套
         * dept_id : 0000
         * bookcode : B000000
         */

        private String password;
        private String userid;
        private String cityname;
        private String citycode;
        private String custid;
        private String regtype;
        private String state;
        private String user_name;
        private String user_code;
        private String adminid;
        private String headimgurl;
        private String netname;
        private String phoneno;
        private String netcode;
        private String vehicle;
        private String usertype;
        private String nettype;
        private String cmpname;
        private String custname;
        private String custcode;
        private String netid;
        private String wxid;
        private String areaname;
        private String cityarea;
        private String nettree;
        private String bookname;
        private String dept_id;
        private String bookcode;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCustid() {
            return custid;
        }

        public void setCustid(String custid) {
            this.custid = custid;
        }

        public String getRegtype() {
            return regtype;
        }

        public void setRegtype(String regtype) {
            this.regtype = regtype;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getAdminid() {
            return adminid;
        }

        public void setAdminid(String adminid) {
            this.adminid = adminid;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getNetname() {
            return netname;
        }

        public void setNetname(String netname) {
            this.netname = netname;
        }

        public String getPhoneno() {
            return phoneno;
        }

        public void setPhoneno(String phoneno) {
            this.phoneno = phoneno;
        }

        public String getNetcode() {
            return netcode;
        }

        public void setNetcode(String netcode) {
            this.netcode = netcode;
        }

        public String getVehicle() {
            return vehicle;
        }

        public void setVehicle(String vehicle) {
            this.vehicle = vehicle;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getNettype() {
            return nettype;
        }

        public void setNettype(String nettype) {
            this.nettype = nettype;
        }

        public String getCmpname() {
            return cmpname;
        }

        public void setCmpname(String cmpname) {
            this.cmpname = cmpname;
        }

        public String getCustname() {
            return custname;
        }

        public void setCustname(String custname) {
            this.custname = custname;
        }

        public String getCustcode() {
            return custcode;
        }

        public void setCustcode(String custcode) {
            this.custcode = custcode;
        }

        public String getNetid() {
            return netid;
        }

        public void setNetid(String netid) {
            this.netid = netid;
        }

        public String getWxid() {
            return wxid;
        }

        public void setWxid(String wxid) {
            this.wxid = wxid;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getCityarea() {
            return cityarea;
        }

        public void setCityarea(String cityarea) {
            this.cityarea = cityarea;
        }

        public String getNettree() {
            return nettree;
        }

        public void setNettree(String nettree) {
            this.nettree = nettree;
        }

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }

        public String getDept_id() {
            return dept_id;
        }

        public void setDept_id(String dept_id) {
            this.dept_id = dept_id;
        }

        public String getBookcode() {
            return bookcode;
        }

        public void setBookcode(String bookcode) {
            this.bookcode = bookcode;
        }
    }
}
