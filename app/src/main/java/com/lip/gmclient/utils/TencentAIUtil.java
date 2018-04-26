package com.lip.gmclient.utils;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TencentAIUtil {

    /*
    接口鉴权->计算步骤
    1.将<key, value>请求参数对按key进行字典升序排序，得到有序的参数对列表N
    2.将列表N中的参数对按URL键值对的格式拼接成字符串，得到字符串T（如：key1=value1&key2=value2），URL键值拼接过程value部分需要URL编码，URL编码算法用大写字母，例如%E8，而不是小写%e8
    3.将应用密钥以app_key为键名，组成URL键值拼接到字符串T末尾，得到字符串S（如：key1=value1&key2=value2&app_key=密钥)
    4.对字符串S进行MD5运算，将得到的MD5值所有字符转换成大写，得到接口请求签名
     */
    public static String getReqSign(String image,String nonce_str,int scene,String time_stamp){
        String T="app_id="+Constant.Tencent_AppID+"&image="+image+"&nonce_str="+nonce_str+"&scene="+scene+"&time_stamp="+time_stamp;
        String S=T+"&app_key="+Constant.Tencent_AppKey;
        String MD5T=Md5Util.md5(S);
        return MD5T.toUpperCase();
    }


    // 将文件转为Base64
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            int length = in.read(bytes);
            base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return base64;
    }
}
