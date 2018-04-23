package com.lip.gmclient.domain;

import java.util.List;

public class VPListBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"vid":1,"url":"111","nexturl":"222","title":"333"},{"vid":2,"url":"222","nexturl":"333","title":"444"}]
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
         * vid : 1
         * url : 111
         * nexturl : 222
         * title : 333
         */

        private int vid;
        private String url;
        private String nexturl;
        private String title;

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getNexturl() {
            return nexturl;
        }

        public void setNexturl(String nexturl) {
            this.nexturl = nexturl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
