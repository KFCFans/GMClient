package com.lip.gmclient.domain;

import java.util.List;

public class PlantListBean {

    /**
     * stauts : 200
     * msg : OK
     * data : [{"pid":1,"pname":"牡丹花","pimg":"mudanhua","psname":"Mu Dan","pdetail":"好一朵美丽的牡丹花","ptype":"zz","plhxg":"zz","pxyfb":"zz"},{"pid":2,"pname":"茉莉花","pimg":"molihua","psname":"Mo Li","pdetail":"好一朵美丽的茉莉花","ptype":"zz","plhxg":"zz","pxyfb":"zz"},{"pid":3,"pname":"菊花","pimg":"juhua","psname":"Ju Hua","pdetail":"好一朵美丽的菊花","ptype":"zz","plhxg":"zz","pxyfb":"zz"},{"pid":4,"pname":"银杏","pimg":"yinxing","psname":"Ginkgo biloba L.","pdetail":"银杏","ptype":"zz","plhxg":"zz","pxyfb":"zz"},{"pid":5,"pname":"雪松","pimg":"xuesong","psname":"Xue Song","pdetail":"我是雪松","ptype":"松科 雪松属","plhxg":"绿化效果极佳","pxyfb":"一食堂附件"},{"pid":10,"pname":"1","pimg":"1528466719248","psname":"2","pdetail":"6","ptype":"3","plhxg":"5","pxyfb":"4"}]
     */

    private int stauts;
    private String msg;
    private List<DataBean> data;

    public int getStauts() {
        return stauts;
    }

    public void setStauts(int stauts) {
        this.stauts = stauts;
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
         * pid : 1
         * pname : 牡丹花
         * pimg : mudanhua
         * psname : Mu Dan
         * pdetail : 好一朵美丽的牡丹花
         * ptype : zz
         * plhxg : zz
         * pxyfb : zz
         */

        private int pid;
        private String pname;
        private String pimg;
        private String psname;
        private String pdetail;
        private String ptype;
        private String plhxg;
        private String pxyfb;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getPimg() {
            return pimg;
        }

        public void setPimg(String pimg) {
            this.pimg = pimg;
        }

        public String getPsname() {
            return psname;
        }

        public void setPsname(String psname) {
            this.psname = psname;
        }

        public String getPdetail() {
            return pdetail;
        }

        public void setPdetail(String pdetail) {
            this.pdetail = pdetail;
        }

        public String getPtype() {
            return ptype;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public String getPlhxg() {
            return plhxg;
        }

        public void setPlhxg(String plhxg) {
            this.plhxg = plhxg;
        }

        public String getPxyfb() {
            return pxyfb;
        }

        public void setPxyfb(String pxyfb) {
            this.pxyfb = pxyfb;
        }
    }
}
