package com.lip.gmclient.domain;

import java.util.List;

public class PlantListBean {


    /**
     * stauts : 200
     * msg : OK
     * data : [{"pid":1,"pname":"牡丹","pimg":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=6e2859b657fbb2fb202650402e234bc1/bd315c6034a85edfba77afc84b540923dc547565.jpg","psname":"Paeonia suffruticosa Andr","pdetail":"一种非常美丽的花~~~~~~~~~~·"},{"pid":2,"pname":"菊花","pimg":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522334501488&di=f2e9411869ee6275bf223861aa6150eb&imgtype=0&src=http%3A%2F%2Fs11.sinaimg.cn%2Fmw690%2Fb52acc99xcd19385a785a%26690","psname":"Ju Hua","pdetail":"大家好我是菊花"},{"pid":3,"pname":"桂花","pimg":"https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=921a11c1566034a83defb0d3aa7a2231/71cf3bc79f3df8dcda3f1751c711728b461028eb.jpg","psname":"GuiHua","pdetail":"大家好我是桂花"}]
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
         * pname : 牡丹
         * pimg : https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=6e2859b657fbb2fb202650402e234bc1/bd315c6034a85edfba77afc84b540923dc547565.jpg
         * psname : Paeonia suffruticosa Andr
         * pdetail : 一种非常美丽的花~~~~~~~~~~·
         */

        private int pid;
        private String pname;
        private String pimg;
        private String psname;
        private String pdetail;

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
    }
}
