package com.lip.gmclient.domain;

import java.util.List;

public class FlowerRecoBean {

    /**
     * ret : 0
     * msg : ok
     * data : {"tag_list":[{"label_id":1006,"label_name":"菊花","label_confd":0.294773},{"label_id":1006,"label_name":"杭菊","label_confd":0.00370199},{"label_id":1006,"label_name":"珊瑚花","label_confd":0.00340214}]}
     */

    private int ret;
    private String msg;
    private DataBean data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TagListBean> tag_list;

        public List<TagListBean> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<TagListBean> tag_list) {
            this.tag_list = tag_list;
        }

        public static class TagListBean {
            /**
             * label_id : 1006
             * label_name : 菊花
             * label_confd : 0.294773
             */

            private int label_id;
            private String label_name;
            private double label_confd;

            public int getLabel_id() {
                return label_id;
            }

            public void setLabel_id(int label_id) {
                this.label_id = label_id;
            }

            public String getLabel_name() {
                return label_name;
            }

            public void setLabel_name(String label_name) {
                this.label_name = label_name;
            }

            public double getLabel_confd() {
                return label_confd;
            }

            public void setLabel_confd(double label_confd) {
                this.label_confd = label_confd;
            }
        }
    }
}
