package com.javacloud.service.dto;

import java.util.HashMap;

/**
 * Created by zhengnanyang on 16/12/2.
 */
public class TranslateDTO {
    public HashMap<String,String> translateResult;

    public HashMap<String, String> getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(HashMap<String, String> translateResult) {
        this.translateResult = translateResult;
    }

}
