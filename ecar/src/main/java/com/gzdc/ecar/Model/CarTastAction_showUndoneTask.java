package com.gzdc.ecar.Model;

import java.util.List;

/**
 * Created by pengzhuoneng on 2017/2/24.
 */

public class CarTastAction_showUndoneTask {
    /**
     * msg : 查询成功
     * isSuccess : true
     * task : [{"id":"d3427825d13d4fb685b44ccff2c34ad8","typenamen":"网点分拨","taskstate":"0","assignid":"d3427825d13d4fb685b44ccff2c34ad8","drivertel":"21","typename":"04","plantime":"2016-09-07","vehicle":"粤154624","taskstaten":"未完成","assignbill":"PN000003201609070010","driver":"肖申克"}]
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
         * id : d3427825d13d4fb685b44ccff2c34ad8
         * typenamen : 网点分拨
         * taskstate : 0
         * assignid : d3427825d13d4fb685b44ccff2c34ad8
         * drivertel : 21
         * typename : 04
         * plantime : 2016-09-07
         * vehicle : 粤154624
         * taskstaten : 未完成
         * assignbill : PN000003201609070010
         * driver : 肖申克
         */

        private String id;
        private String typenamen;
        private String taskstate;
        private String assignid;
        private String drivertel;
        private String typename;
        private String plantime;
        private String vehicle;
        private String taskstaten;
        private String assignbill;
        private String driver;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypenamen() {
            return typenamen;
        }

        public void setTypenamen(String typenamen) {
            this.typenamen = typenamen;
        }

        public String getTaskstate() {
            return taskstate;
        }

        public void setTaskstate(String taskstate) {
            this.taskstate = taskstate;
        }

        public String getAssignid() {
            return assignid;
        }

        public void setAssignid(String assignid) {
            this.assignid = assignid;
        }

        public String getDrivertel() {
            return drivertel;
        }

        public void setDrivertel(String drivertel) {
            this.drivertel = drivertel;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getPlantime() {
            return plantime;
        }

        public void setPlantime(String plantime) {
            this.plantime = plantime;
        }

        public String getVehicle() {
            return vehicle;
        }

        public void setVehicle(String vehicle) {
            this.vehicle = vehicle;
        }

        public String getTaskstaten() {
            return taskstaten;
        }

        public void setTaskstaten(String taskstaten) {
            this.taskstaten = taskstaten;
        }

        public String getAssignbill() {
            return assignbill;
        }

        public void setAssignbill(String assignbill) {
            this.assignbill = assignbill;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }
    }
}
