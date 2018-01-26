package com.gzdc.ecar.Model;

import java.util.List;

/**
 * Created by pzn on 2017/1/11 0011.
 */

public class NetPointModel {

    /**
     * msg : 查询成功
     * isSuccess : true
     * netpoint : [{"id":"1807dc02e1e54d9094b0f970c077d4e2","pointxy":"113.017003,23.243181","sort_code":"0","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"3139bfd83d4a47e5ba47217a8fe494ad","pointxy":"112.955487,23.085293","sort_code":"1","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"bca0e64c8f3648f29734be3eff268866","pointxy":"112.979058,23.036346","sort_code":"2","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"6775eba087d14df6b7e137bf257582e6","pointxy":"113.067595,22.995367","sort_code":"3","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"f18bc0875b0d4bf68e19df2730e2bad8","pointxy":"113.164756,22.983656","sort_code":"4","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"aa9dacd3cf5946098dbf03a42c84bcbf","pointxy":"113.206725,23.06029","sort_code":"5","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"03d7e36bd16146ce8fd82e0ba4628bd2","pointxy":"113.223397,23.181005","sort_code":"6","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"7766d1f80cd743a0a912240effbc05fb","pointxy":"113.240645,23.235211","sort_code":"7","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"c1e5855353a14b1cab10862975c2deed","pointxy":"113.188327,23.27718","sort_code":"8","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"7a591e078bc140e4abf76f1589727b60","pointxy":"113.134285,23.289396","sort_code":"9","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"},{"id":"afe3c36e4dba4e149004839579ca2ca5","pointxy":"113.017003,23.243181","sort_code":"10","mainid":"be55c01fc3ea47529e7836b2a2bdb3f7"}]
     */

    private String msg;
    private boolean isSuccess;
    /**
     * id : 1807dc02e1e54d9094b0f970c077d4e2
     * pointxy : 113.017003,23.243181
     * sort_code : 0
     * mainid : be55c01fc3ea47529e7836b2a2bdb3f7
     */

    private List<NetpointBean> netpoint;

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

    public List<NetpointBean> getNetpoint() {
        return netpoint;
    }

    public void setNetpoint(List<NetpointBean> netpoint) {
        this.netpoint = netpoint;
    }

    public static class NetpointBean {
        private String id;
        private String pointxy;
        private String sort_code;
        private String mainid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPointxy() {
            return pointxy;
        }

        public void setPointxy(String pointxy) {
            this.pointxy = pointxy;
        }

        public String getSort_code() {
            return sort_code;
        }

        public void setSort_code(String sort_code) {
            this.sort_code = sort_code;
        }

        public String getMainid() {
            return mainid;
        }

        public void setMainid(String mainid) {
            this.mainid = mainid;
        }
    }
}
