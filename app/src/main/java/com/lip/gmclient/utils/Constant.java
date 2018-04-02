package com.lip.gmclient.utils;

import java.net.URL;

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

    // 使用Token登陆
    public static final String URL_TOKENLOGIN=URL_HEAD+"/security/tokenlogin";

    // 获取植物信息列表
    public static final String URL_PLANTLIST=URL_HEAD+"/plant/list";

    // 获取用户信息
    public static final String URL_USERINFO= URL_HEAD+"/userinfo/get";

    // 提交反馈信息
    public static final String URL_FEEDBACK=URL_HEAD+"/feedback/commit";
}
