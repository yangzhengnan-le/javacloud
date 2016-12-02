package com.javacloud.service;

import com.javacloud.service.baidu.TransApi;
import org.springframework.stereotype.Service;

/**
 * Created by zhengnanyang on 16/12/2.
 */
@Service
public class BaiduService {
    private static final String APP_ID = "20161201000033258";
    private static final String SECURITY_KEY = "qQ52PTKVUDyNMCBwwqOY";


    public String translate(String query)
    {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        return api.getTransResult(query, "auto", "auto");
    }

}
