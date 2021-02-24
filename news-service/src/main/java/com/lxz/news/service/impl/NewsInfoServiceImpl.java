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

        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        String json = results.getBody();
        JSONObject jsonObject  =JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("data");
        List<New> listNew=new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            New news=new New();
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            news.setTitle(jsonObject1.getString("title"));
            news.setDate(jsonObject1.getString("date"));
            if(jsonObject1.getString("thumbnail_pic_s").isEmpty()){
                news.setThumbnail_pic_s("./dist/img/photo1.png");
            }else {
                news.setThumbnail_pic_s(jsonObject1.getString("thumbnail_pic_s"));
            }
            if(jsonObject1.getString("url").isEmpty()){
                news.setUrl("https://www.baidu.com/");
            }else {
                news.setUrl(jsonObject1.getString("url"));
            }
            listNew.add(news);
        }

        return listNew;
    }

    @Override
    public List<New> listNewsInfo() {
        String url="http://v.juhe.cn/toutiao/index?type=toutiao&key="+juhekey;
        System.out.println(url);

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
    public List<New> listNewByClassify(String channel) {
        String channelId=tranChannelUtil.channel(channel);
        String url="https://route.showapi.com/109-35?showapi_appid=514404&showapi_sign="+wanweikey+"&channelId="+channelId+"&maxResult=20";
//        String url="https://route.showapi.com/109-35?showapi_appid=545832&showapi_sign=5b81050cc21b40febc9e93b2b2cd4e64&channelId="+channelId+"&maxResult=20";
        System.out.println(url);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json = results.getBody();
        JSONObject jsonObject  =JSONObject.parseObject(json);

        JSONArray jsonArray = jsonObject.getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist");
        List<New> listNew=new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            New news=new New();
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            news.setTitle(jsonObject1.getString("title"));
            news.setDate(jsonObject1.getString("pubDate"));
            if(jsonObject1.getJSONArray("imageurls").size() == 0){
                news.setThumbnail_pic_s("./dist/img/photo1.png");
            }else if(jsonObject1.getJSONArray("imageurls").getJSONObject(0).getString("url").isEmpty()){
                news.setThumbnail_pic_s("./dist/img/photo1.png");
            }else {
                news.setThumbnail_pic_s(jsonObject1.getJSONArray("imageurls").getJSONObject(0).getString("url"));
            }
            if(jsonObject1.getString("link").isEmpty()){
                news.setUrl("https://www.baidu.com/");
            }else {
                news.setUrl(jsonObject1.getString("link"));
            }
            listNew.add(news);
        }

        return listNew;
    }

    @Override
    public List<New> listNew() {
        String url="https://api.apiopen.top/getWangYiNews?count=100";
        System.out.println(url);

        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        String json = results.getBody();
        JSONObject jsonObject  =JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        List<New> listNew=new ArrayList<>();

        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            New news=new New();
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            news.setTitle(jsonObject1.getString("title"));
            news.setDate(jsonObject1.getString("passtime"));
            if(jsonObject1.getString("image").isEmpty()){
                news.setThumbnail_pic_s("./dist/img/photo1.png");
            }else {
                news.setThumbnail_pic_s(jsonObject1.getString("image"));
            }
            if(jsonObject1.getString("path").isEmpty()){
                news.setUrl("https://www.baidu.com/");
            }else {
                news.setUrl(jsonObject1.getString("path"));
            }
            listNew.add(news);
        }

        return listNew;
    }
}
