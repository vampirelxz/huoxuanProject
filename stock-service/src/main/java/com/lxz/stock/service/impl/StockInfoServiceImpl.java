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
import org.apache.commons.lang.StringUtils;
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
import java.util.stream.Collectors;

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
        List<StockBO> strings = stockListMapper.listPersonalStock(createId);
        List<String> idList = strings.stream().map(StockBO::getStockId).collect(Collectors.toList());
        String stringCode = StringUtils.join(idList.toArray(), ",");
        URL u = new URL("http://route.showapi.com/131-46?showapi_appid=514404&stocks="+stringCode+"&needIndex=0&showapi_sign=986324c42ad044e692e6a3e8fc1e51cd");
        System.out.println(u.toString());
        String stockInfo = stockUtil.getStockInfo(u);

        JSONArray jsonArray = JSONObject.parseObject(stockInfo).getJSONObject("showapi_res_body").getJSONArray("list");
        List<PersonalStock> personalStocks = new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            PersonalStock personalStock = new PersonalStock();
            personalStock.setCode(jsonObject1.getString("code"));
            personalStock.setName(jsonObject1.getString("name"));
            personalStock.setNowPrice(jsonObject1.getString("nowPrice"));
            personalStock.setTurnover(jsonObject1.getString("turnover"));
            personalStock.setDiff_money(jsonObject1.getString("diff_money"));
            personalStock.setDiff_rate(jsonObject1.getFloat("diff_rate"));
            personalStock.setAll_value(jsonObject1.getString("all_value"));
            personalStock.setCirculation_value(jsonObject1.getString("circulation_value"));
            personalStock.setTotalcapital(jsonObject1.getString("totalcapital"));
            personalStock.setCurrcapital(jsonObject1.getString("currcapital"));
            personalStock.setPe(jsonObject1.getString("pe"));
            personalStock.setPb(jsonObject1.getString("pb"));
            personalStocks.add(personalStock);
        }

        return personalStocks;
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

    @Override
    public void insertSelfStock(int createId, String stockId) {
        if(stockId == null || stockId.isEmpty() || createId == 0 ){
            return;
        }
        if(stockListMapper.checkPersonalStock(createId,stockId).isEmpty()){
            stockListMapper.insertStockById(createId,stockId);
        }

    }

    @Override
    public void deleteSelfStock(int createId, String stockId) {
        if(stockId == null || stockId.isEmpty() || createId == 0){
            return;
        }
        stockListMapper.deleteStockById(createId,stockId);
    }



}
