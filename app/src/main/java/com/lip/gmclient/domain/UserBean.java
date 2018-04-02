package com.lip.gmclient.domain;

public class UserBean {

    /**
     * status : 200
     * msg : OK
     * data : {"uid":"15061883391","nickname":"滕佶祺","gender":0,"password":"00b14ce194bd750b69b499eae68fd374","priority":0,"accesstoken":"1234","avatar":"xxx","achievement":100}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 15061883391
         * nickname : 滕佶祺
         * gender : 0
         * password : 00b14ce194bd750b69b499eae68fd374
         * priority : 0
         * accesstoken : 1234
         * avatar : xxx
         * achievement : 100
         */

        private String uid;
        private String nickname;
        private int gender;
        private String password;
        private int priority;
        private String accesstoken;
        private String avatar;
        private int achievement;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getAccesstoken() {
            return accesstoken;
        }

        public void setAccesstoken(String accesstoken) {
            this.accesstoken = accesstoken;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getAchievement() {
            return achievement;
        }

        public void setAchievement(int achievement) {
            this.achievement = achievement;
        }
    }
}
