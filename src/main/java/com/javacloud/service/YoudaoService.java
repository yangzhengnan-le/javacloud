package com.javacloud.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
public class YoudaoService {
    public String translate(String query)
    {
        query=query.replace(" ", "%20");
        String url="http://fanyi.youdao.com/openapi.do?keyfrom=java120&key=1819916083&type=data&doctype=json&version=1.1&only=translate&q="+query;
        //实例化httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //实例化get方法
        HttpGet httpget = new HttpGet(url);
        //请求结果
        CloseableHttpResponse response = null;
        String content ="";
        try {
            //执行get方法
            response = httpclient.execute(httpget);
            if(response.getStatusLine().getStatusCode()==200){
                content = EntityUtils.toString(response.getEntity(),"utf-8");
                JSONObject object= JSON.parseObject(content);
                JSONArray translation=object.getJSONArray("translation");
                content= (String) translation.get(0);
              //  System.out.println(translation.get(0));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }



}
