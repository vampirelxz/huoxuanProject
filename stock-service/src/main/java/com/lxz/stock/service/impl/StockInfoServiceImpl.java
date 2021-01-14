package com.lxz.stock.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/14/11:07
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.service.impl
 * 文件描述: @Description: 股票信息获取实现类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxz.stock.config.RestTemplateConfig;
import com.lxz.stock.entity.BuyStock;
import com.lxz.stock.entity.Stock;
import com.lxz.stock.service.StockInfoService;
import com.lxz.stock.utils.HttpUtil;
import com.lxz.stock.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 包名称： com.lxz.stock.service.impl
 * 类名称：StockServiceInfoImpl
 * 类描述：股票信息获取实现类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/14/11:07
 */
@Service
public class StockInfoServiceImpl implements StockInfoService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private JsonUtil jsonUtil;

    @Value("${baidu.stock.AppCode}")
    public String appcode;

    @Override
    public BuyStock infoRealTime(int code) {
        String url="http://gwgp-5bmrebmatbr.n.bdcloudapi.com/realtime/?symbols="+code;
        System.out.println(url);
        HttpEntity<String> requestEntity = httpUtil.InsertBaiduApiAppCode(appcode);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        BuyStock buyStock=jsonUtil.jsonToObject(results,BuyStock.class);
        return buyStock;
    }

    @Override
    public Stock infoToday(int code) {
        String url="http://gwgp-5bmrebmatbr.n.bdcloudapi.com/today?symbols="+code;
        System.out.println(url);
        HttpEntity<String> requestEntity = httpUtil.InsertBaiduApiAppCode(appcode);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        Stock stock=jsonUtil.jsonToObject(results,Stock.class);
        return stock;
    }


}
