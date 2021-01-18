package com.lxz.lifetools.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/18/16:19
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.service.impl
 * 文件描述: @Description: 快递服务类实现
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxz.lifetools.entity.Express;
import com.lxz.lifetools.entity.ExpressInfo;
import com.lxz.lifetools.service.ExpressService;
import com.lxz.lifetools.utils.ExpressUtil;
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
 * 类名称：ExpressServiceImpl
 * 类描述：快递服务类实现
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/18/16:19
 */

@Service
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExpressUtil expressUtil;

    @Autowired
    Express express;



    @Override
    public Express listExpress(String num, String com) {
        try {
            String url;
            if(com == null){
                url = "https://v1.alapi.cn/api/kd?number=" + num;
                System.out.println(url);
            }else {
                com = expressUtil.companyTran(com);
                url = "https://v1.alapi.cn/api/kd?number=" + num + "&com=" + com;
                System.out.println(url);
            }
            ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            String json = results.getBody();
            JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("data");
            JSONArray jsonArray = jsonObject.getJSONArray("info");
            List<ExpressInfo> expressInfo = new ArrayList<>();
            for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
                JSONObject jsonObject1 = (JSONObject) iterator.next();
                ExpressInfo content = jsonObject1.toJavaObject(ExpressInfo.class);
                expressInfo.add(content);
            }
            express.setCom(jsonObject.getString("com"));
            express.setNum(jsonObject.getString("nu"));
            express.setStatus(jsonObject.getInteger("status"));
            express.setInfo(expressInfo);
            return express;
        }catch (Exception e){
            e.printStackTrace();
        }
        express.setStatus(0);
        express.setCom("请输入正确的快递编码和公司全称");
        return express;
    }
}
