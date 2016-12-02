package com.javacloud.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import com.javacloud.domain.User;
import com.javacloud.security.AuthoritiesConstants;
import com.javacloud.service.BaiduService;

import com.javacloud.service.GoogleService;
import com.javacloud.service.YoudaoService;
import com.javacloud.service.dto.TranslateDTO;
import com.javacloud.web.rest.util.HeaderUtil;
import com.javacloud.web.rest.vm.ManagedUserVM;

import org.json.JSONString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;



/**
 * Created by zhengnanyang on 16/12/2.
 */
@RestController
@RequestMapping("/api")
public class TranslateResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    @Inject
    private BaiduService baiduService;
    @Inject
    private YoudaoService youdaoService;
    @Inject
    private GoogleService googleService;

    /**
     * get  /translate  : translate words.
     * <p>
     * translate words by baidu
     * translate words by google.
     * translate words by youdao.
     * </p>
     *
     * @param query word to query
     * @return the ResponseEntity with status 200(success)  or with status 400 (Bad Request)
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/translate",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<TranslateDTO> translate(@RequestParam String query) throws URISyntaxException {
        log.debug("REST request to save User : {}", query);

        String baiduWords=baiduService.translate(query);
        JSONObject object = JSON.parseObject(baiduWords);
       // JSONObject dst=object.getJSONObject("dst");
        JSONArray trans_result=object.getJSONArray("trans_result");
        JSONObject dst=trans_result.getJSONObject(0);
        String baiduString= (String) dst.get("dst");

        TranslateDTO translateDTO=new TranslateDTO();
        HashMap<String,String> resultMap=new HashMap();
        resultMap.put("baidu",baiduString);
        String youdaoString=youdaoService.translate(query);
        resultMap.put("youdao",youdaoString);
        String googleString=googleService.translate(query);
        resultMap.put("google",googleString);
        translateDTO.setTranslateResult(resultMap);



       return new ResponseEntity<>(translateDTO, HttpStatus.OK);


    }
}
