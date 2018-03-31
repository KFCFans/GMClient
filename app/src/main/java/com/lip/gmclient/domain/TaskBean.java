package com.lip.gmclient.domain;

import java.util.List;

public class TaskBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"tid":1,"aid":1,"rtype":0,"iid":1,"stime":1522324585000,"etime":1522497389000,"pid":1,"tstatus":0,"tname":"维护植物","tpic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522334624024&di=4c07db57fd1bb8e5ff661cdf452c0d11&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F377adab44aed2e732730e90a8d01a18b87d6fa3f.jpg"},{"tid":2,"aid":2,"rtype":1,"iid":2,"stime":1522380900000,"etime":1524800104000,"pid":2,"tstatus":0,"tname":"大家好我是标题","tpic":"%2F377adab44aed2e732730e90a8d01a18b87d6fa3f.jpg"}]
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
         * tid : 1
         * aid : 1
         * rtype : 0
         * iid : 1
         * stime : 1522324585000
         * etime : 1522497389000
         * pid : 1
         * tstatus : 0
         * tname : 维护植物
         * tpic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522334624024&di=4c07db57fd1bb8e5ff661cdf452c0d11&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F377adab44aed2e732730e90a8d01a18b87d6fa3f.jpg
         */

        private int tid;
        private int aid;
        private int rtype;
        private int iid;
        private long stime;
        private long etime;
        private int pid;
        private int tstatus;
        private String tname;
        private String tpic;

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getRtype() {
            return rtype;
        }

        public void setRtype(int rtype) {
            this.rtype = rtype;
        }

        public int getIid() {
            return iid;
        }

        public void setIid(int iid) {
            this.iid = iid;
        }

        public long getStime() {
            return stime;
        }

        public void setStime(long stime) {
            this.stime = stime;
        }

        public long getEtime() {
            return etime;
        }

        public void setEtime(long etime) {
            this.etime = etime;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getTstatus() {
            return tstatus;
        }

        public void setTstatus(int tstatus) {
            this.tstatus = tstatus;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getTpic() {
            return tpic;
        }

        public void setTpic(String tpic) {
            this.tpic = tpic;
        }
    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
