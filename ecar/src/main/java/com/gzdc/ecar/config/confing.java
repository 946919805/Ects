package com.gzdc.ecar.config;

/**
 * Created by pzn on 2016/7/26.
 */
public class confing {
//        public static String path = "http://139.196.254.50:10086/ECTS";
//        public static String path = "http://192.168.1.10:8088/ECTS";
//    public static String path = "http://192.168.1.200:8080/ECTS";
    public static String path = "http://121.201.61.38:8087/ECTS";
//public static String path = "http://192.168.1.24:8080/ECTS/";

    public  static  String bookcode="b000001";
//    public static String path = "http://192.168.1.17:8080/ECTS";
    public static String register = "/car/CarLoginAction_registUser.do";
    public static String login = "/car/CarLoginAction_login.do";
    public static String selectcode = "/car/CarLoginAction_shownetcode.do";
    public static String querymsrun = "/car/CarLoginAction_querytmsrun.do";
    public static String CarLoginAction_getNetPoint = "/car/CarLoginAction_getNetPoint.do";
    public static String CarTastAction_showItemPosition = "/car/CarTastAction_showItemPosition.do";
    public static String CarTastAction_showtask = "/car/CarTastAction_showtask.do";
    public static String CarTastAction_startTasktime = "/car/CarTastAction_startTasktime.do";
    public static String CarTastAction_showtaskInfo = "/car/CarTastAction_showtaskInfo.do";
    public static String CarTastAction_insure = "/car/CarTastAction_insure.do";//state=1确认收货 state=0拒收
    public static String CarTastAction_saveRecvInfo = "/car/CarTastAction_saveRecvInfo.do";
    public static String CarTastAction_uploadGis = "/car/CarTastAction_uploadGis.do";
    public static String CarTastAction_showUndoneTask = "/car/CarTastAction_showUndoneTask.do";
    public static String CarTastAction_showUndoneTask2 = "/car/CarTastAction_showUndoneTask.do2";
    public static String DriverManageAction_driverSign = "/car/DriverManageAction_driverSign.do";
    public static String DriverManageAction_UpCarGis = "/car/DriverManageAction_UpCarGis.do";
    public static String CarTastAction_itemScheTask = "/car/CarTastAction_itemScheTask.do";//接货调度
    public static String CarTastAction_cancelScheTask = "/car/CarTastAction_cancelScheTask.do";
    public static String CarTastAction_doneScheTask = "/car/CarTastAction_doneScheTask.do";//交单确认
    public static String CarTastAction_startScheTask = "/car/CarTastAction_startScheTask.do";// 接单确认
    public static String CarTastAction_sureScheTask = "/car/CarTastAction_sureScheTask.do";// 发车确认
    public static String CarTastAction_sureTakeItem = "/car/CarTastAction_sureTakeItem.do";// 确认收货

    public static String CarTastAction_showScheTaskItem = "/car/CarTastAction_showScheTaskItem.do";// 确认收货
    public static String FileUpAction_upFile = "/sys/FileUpAction_upFile.do";// 确认收货
    public static String FileUpAction_upImage = "/sys/FileUpAction_upImage.do";// 确认收货
    public static String FileUpAction_appUpLoad = "/sys/FileUpAction_appUpLoad.do";// 图片上传
    public static String DriverManageAction_UpWarnGis = "/car/DriverManageAction_UpWarnGis.do";//报警
     public static String CarTastAction_saveOrderBill = "/car/CarTastAction_saveOrderBill.do";
    public static String CarTastAction_cancelOrderBill = "/car/CarTastAction_cancelOrderBill.do";
    //送货单单件信息显示
    public static String CarTastAction_showSendBill1 = "/car/CarTastAction_showSendBill1.do";
    //送货单单件签收保存
    public static String CarTastAction_sendBillSign1 = "/car/CarTastAction_sendBillSign1.do";
    //送货单多件信息显示
    public static String CarTastAction_showSendBill2 = "/car/CarTastAction_showSendBill2.do";
    //送货单多件签收保存
    public static String CarTastAction_sendBillSign2 = "/car/CarTastAction_sendBillSign2.do";
//干线签收formtype='2'
    public static String CarTastAction_runBillSign2 = "/car/CarTastAction_runBillSign2.do";
    //获取干线运输单详情formtype='1'
    public static String CarTastAction_showRunBill1 = "/car/CarTastAction_showRunBill1.do";
    //获取干线运输单详情formtype='2'
    public static String CarTastAction_showRunBill2 = "/car/CarTastAction_showRunBill2.do";
    //billtype='2、3'确认
    public static String CarTastAction_sureLoadBeg = "/car/CarTastAction_sureLoadBeg.do";
    //任务
    public static String CarTastAction_itemScheSign = "/car/CarTastAction_itemScheSign.do";
    //获取回单信息
    public static String CarTastAction_showSendBack = "/car/CarTastAction_showSendBack.do";
    //确认回单信息
    public static String CarTastAction_saveSendBack = "/car/CarTastAction_saveSendBack.do";
    public static String CustAddrAction_getCustSendName = "/cust/CustAddrAction_getCustSendName.do";




}
