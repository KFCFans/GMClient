package com.lip.gmclient.utils;

public class Constant {

    /**
     * 全局TAG
     */
    public static final String TAG="lip";

    /**
     * SharePreference
     */
    public static final String ACCESSTOKEN="ACCESSTOKEN";
    public static final String USERID="USERID";


    /**
     * 网络请求URL
     */
    public static final String URL_HEAD="http://10.0.2.2:8080/gm";

    // 获取任务列表
    public static final String URL_TASKLIST=URL_HEAD+"/task/list";

    // 获取地区列表
    public static final String URL_AREALIST=URL_HEAD+"/area/list";

    // 使用密码登陆
    public static final String URL_PWDLOGIN=URL_HEAD+"/security/pwdlogin";


}
