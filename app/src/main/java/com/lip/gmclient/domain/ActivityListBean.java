package com.lip.gmclient.domain;

import java.util.List;

public class ActivityListBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"avid":2,"avname":"第一个活动","avplace":"第一个地点","avstime":1525107600000,"avpic":"1524394780799","avdetail":"第一个活动详情","avetime":1525197600000,"avstatus":null}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * avid : 2
         * avname : 第一个活动
         * avplace : 第一个地点
         * avstime : 1525107600000
         * avpic : 1524394780799
         * avdetail : 第一个活动详情
         * avetime : 1525197600000
         * avstatus : null
         */

        private int avid;
        private String avname;
        private String avplace;
        private long avstime;
        private String avpic;
        private String avdetail;
        private long avetime;
        private Object avstatus;

        public int getAvid() {
            return avid;
        }

        public void setAvid(int avid) {
            this.avid = avid;
        }

        public String getAvname() {
            return avname;
        }

        public void setAvname(String avname) {
            this.avname = avname;
        }

        public String getAvplace() {
            return avplace;
        }

        public void setAvplace(String avplace) {
            this.avplace = avplace;
        }

        public long getAvstime() {
            return avstime;
        }

        public void setAvstime(long avstime) {
            this.avstime = avstime;
        }

        public String getAvpic() {
            return avpic;
        }

        public void setAvpic(String avpic) {
            this.avpic = avpic;
        }

        public String getAvdetail() {
            return avdetail;
        }

        public void setAvdetail(String avdetail) {
            this.avdetail = avdetail;
        }

        public long getAvetime() {
            return avetime;
        }

        public void setAvetime(long avetime) {
            this.avetime = avetime;
        }

        public Object getAvstatus() {
            return avstatus;
        }

        public void setAvstatus(Object avstatus) {
            this.avstatus = avstatus;
        }
    }
}
