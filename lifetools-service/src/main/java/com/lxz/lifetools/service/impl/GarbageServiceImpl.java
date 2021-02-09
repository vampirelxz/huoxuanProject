package com.lxz.lifetools.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/21/11:09
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.service.impl
 * 文件描述: @Description: 垃圾分类实现类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxz.lifetools.entity.Garbage;
import com.lxz.lifetools.service.GarbageService;
import com.lxz.lifetools.utils.GarbageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 包名称： com.lxz.lifetools.service.impl
 * 类名称：Garbage sorting
 * 类描述：垃圾分类实现类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/21/11:09
 */

@Service
public class GarbageServiceImpl implements GarbageService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Garbage garbage;
    @Autowired
    GarbageUtil garbageUtil;

    @Override
    public List<Garbage> garbageSorting(String garbage) {
        String url="https://v1.alapi.cn/api/lajifenlei?name="+garbage;
        System.out.println(url);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json = results.getBody();
        JSONArray jsonArray = JSONObject.parseObject(json).getJSONArray("data");
        List<Garbage> garbageInfo = new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            Garbage content = jsonObject1.toJavaObject(Garbage.class);
            content.setType(garbageUtil.typeToName(content.getType()));
            garbageInfo.add(content);
        }
        if(garbageInfo.isEmpty()){
            Garbage garbageF = new Garbage();
            garbageF.setExplain("对不起，暂未收入此垃圾信息");
            garbageF.setContain("对不起，暂未收入此垃圾信息");
            garbageF.setTip("对不起，暂未收入此垃圾信息");
            garbageF.setType("未识别垃圾");
            garbageF.setName("未识别垃圾");
            garbageInfo.add(garbageF);
        }
        return garbageInfo;
    }
}
