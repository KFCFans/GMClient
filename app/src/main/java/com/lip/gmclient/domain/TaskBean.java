package com.lip.gmclient.domain;

import java.util.List;

public class TaskBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"tid":1,"aid":1,"rtype":0,"stime":1522895031000,"etime":1527819834000,"pid":1,"tstatus":0,"tname":"维护植物","tpic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522904890581&di=49a6e903e0a59b093f75ca914a2b1273&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fc83d70cf3bc79f3d9e86882db8a1cd11728b2943.jpg","uid":"15061883391","tdetail":"教育局领导要来我校实地考察，此项任务必须好好完成，否则工资不存在的！教育局领导要来我校实地考察，此项任务必须好好完成，否则工资不存在的！"}]
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
         * stime : 1522895031000
         * etime : 1527819834000
         * pid : 1
         * tstatus : 0
         * tname : 维护植物
         * tpic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522904890581&di=49a6e903e0a59b093f75ca914a2b1273&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fc83d70cf3bc79f3d9e86882db8a1cd11728b2943.jpg
         * uid : 15061883391
         * tdetail : 教育局领导要来我校实地考察，此项任务必须好好完成，否则工资不存在的！教育局领导要来我校实地考察，此项任务必须好好完成，否则工资不存在的！
         */

        private int tid;
        private int aid;
        private int rtype;
        private long stime;
        private long etime;
        private int pid;
        private int tstatus;
        private String tname;
        private String tpic;
        private String uid;
        private String tdetail;

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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTdetail() {
            return tdetail;
        }

        public void setTdetail(String tdetail) {
            this.tdetail = tdetail;
        }
    }
}
