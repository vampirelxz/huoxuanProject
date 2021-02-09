package com.lxz.news.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/15/13:31
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.news.service.impl
 * 文件描述: @Description: 实现接口
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxz.news.entity.ClassifyNew;
import com.lxz.news.entity.New;
import com.lxz.news.service.NewsInfoService;
import com.lxz.news.utils.TranChannelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 包名称： com.lxz.news.service.impl
 * 类名称：NewsInfoServiceImpl
 * 类描述：实现接口
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/15/13:31
 */
@Service
public class NewsInfoServiceImpl implements NewsInfoService {

    @Value("${juhe.news.key}")
    String juhekey;

    @Value("${wanwei.news.key}")
    String wanweikey;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TranChannelUtil tranChannelUtil;

    @Override
    public List<New> listNewsInfo(String type) {
        String url="http://v.juhe.cn/toutiao/index?type="+type+"&key="+juhekey;
        System.out.println(url);
        //String json =restTemplate.getForObject(url,Object.class);

        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        String json = results.getBody();
        JSONObject jsonObject  =JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("data");
        List<New> listNew=new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            New aNew = jsonObject1.toJavaObject(New.class);
            listNew.add(aNew);
        }

        return listNew;
    }

    @Override
    public List<New> listNewsInfo() {
        String url="http://v.juhe.cn/toutiao/index?type=toutiao&key="+juhekey;
        System.out.println(url);
        //String json =restTemplate.getForObject(url,Object.class);

        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        String json = results.getBody();
        JSONObject jsonObject  =JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("data");
        List<New> listNew=new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            New aNew = jsonObject1.toJavaObject(New.class);
            listNew.add(aNew);
        }

        return listNew;
    }

    @Override
    public List<ClassifyNew> listNewByClassify(String channel) {
//        String url = "https://route.showapi.com/109-35";
//        JSONObject postData = new JSONObject();
//        postData.put("showapi_appid", "514404");
//        postData.put("showapi_sign", wanweikey);
//        postData.put("channelId", channelId);
//        JSONObject jsonObject = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
//        System.out.println(jsonObject.toString());
        String channelId=tranChannelUtil.channel(channel);
        String url="https://route.showapi.com/109-35?showapi_appid=514404&showapi_sign="+wanweikey+"&channelId="+channelId;
        System.out.println(url);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json = results.getBody();
        JSONObject jsonObject  =JSONObject.parseObject(json);

        JSONArray jsonArray = jsonObject.getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist");
        List<ClassifyNew> listNew=new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            ClassifyNew classifyNew = jsonObject1.toJavaObject(ClassifyNew.class);
            listNew.add(classifyNew);
        }

        return listNew;
    }
}
