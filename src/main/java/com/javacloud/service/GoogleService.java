package com.javacloud.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javacloud.service.google.TranslateUtil;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by zhengnanyang on 16/12/2.
 */
@Service
public class GoogleService {
    public String translate(String query)  {
        //String content =TranslateUtil.translate("how old are you","en","zh-cn");
        try {
            return TranslateUtil.translate(query,"en","zh-cn");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static void main(String[] args) throws Exception {
//        GoogleService gs=new GoogleService();
//        String result=gs.translate("how old are you!");
//        System.out.println(result);
//        //System.out.println("sucess:"+TranslateUtil.translate("how beautiful are you","en","zh-cn"));
//    }
}
