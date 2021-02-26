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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxz.stock.dao.StockListMapper;
import com.lxz.stock.entity.*;
import com.lxz.stock.service.StockInfoService;
import com.lxz.stock.utils.HttpUtil;
import com.lxz.stock.utils.JsonUtil;
import com.lxz.stock.utils.StockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @Autowired
    private StockUtil stockUtil;

    @Resource
    private StockListMapper stockListMapper;

    @Value("${baidu.stock.AppCode}")
    public String appcode;

    @Value("${wanwei.stock.key}")
    public String key;

    @Value("${wanwei.stock.appId}")
    public String appId;

    @Override
    public BuyStock infoRealTime(String code) {
        String url="http://gwgp-5bmrebmatbr.n.bdcloudapi.com/realtime/?symbols="+code;
        System.out.println(url);
        HttpEntity<String> requestEntity = httpUtil.InsertBaiduApiAppCode(appcode);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        BuyStock buyStock=jsonUtil.jsonToObject(results,BuyStock.class);
        return buyStock;
    }

    @Override
    public Stock infoToday(String code) {
        String url="http://gwgp-5bmrebmatbr.n.bdcloudapi.com/today?symbols="+code;
        System.out.println(url);
        HttpEntity<String> requestEntity = httpUtil.InsertBaiduApiAppCode(appcode);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        Stock stock=jsonUtil.jsonToObject(results,Stock.class);
        return stock;
    }

    @Override
    public List<PersonalStock> selfStock(int createId) throws IOException {
        String stringCode=null;
        List<StockBO> strings = stockListMapper.listPersonalStock(createId);
        for(int i=0;i<strings.size();i++){

            stringCode += strings.get(i).getStockId()+",";
        }
        URL u = new URL("http://route.showapi.com/131-46?showapi_appid=514404&stocks="+stringCode+"&needIndex=0&showapi_sign=986324c42ad044e692e6a3e8fc1e51cd");
        String stockInfo = stockUtil.getStockInfo(u);
        System.out.println(stockInfo);

        return null;
    }

    @Override
    public List<BaseStock> baseStock() throws IOException {
        URL u = new URL("http://route.showapi.com/131-46?showapi_appid="+appId+"&stocks=0&needIndex=1&showapi_sign="+key);
        String stockInfo = stockUtil.getStockInfo(u);
        JSONArray jsonArray = JSONObject.parseObject(stockInfo).getJSONObject("showapi_res_body").getJSONArray("indexList");
        List<BaseStock> baseStockInfo = new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            BaseStock content = jsonObject1.toJavaObject(BaseStock.class);
            baseStockInfo.add(content);
        }
        return baseStockInfo;
    }


}
