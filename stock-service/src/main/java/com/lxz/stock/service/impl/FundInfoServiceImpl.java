package com.lxz.stock.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/3/13:54
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.service.impl
 * 文件描述: @Description: 基金实现类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxz.stock.dao.FundListMapper;
import com.lxz.stock.entity.*;
import com.lxz.stock.service.FundInfoService;
import com.lxz.stock.utils.HttpUtil;
import com.lxz.stock.utils.JsonUtil;
import com.lxz.stock.utils.StockUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 包名称： com.lxz.stock.service.impl
 * 类名称：FundInfoServiceImpl
 * 类描述：基金实现类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/3/13:54
 */

@Service
public class FundInfoServiceImpl implements FundInfoService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private StockUtil stockUtil;


    @Resource
    private FundListMapper fundListMapper;

    @Value("${baidu.stock.AppCode}")
    public String appcode;

    @Value("${wanwei.stock.key}")
    public String key;

    @Value("${wanwei.stock.appId}")
    public String appId;



    @Override
    public List<Fund> rankFund() throws IOException {
        URL u = new URL("http://route.showapi.com/1807-1?showapi_appid="+appId+"&type=&sort=unit_net_value:-1&maxResult=20&page=1&showapi_sign="+key);
        String rankFundInfo = stockUtil.getStockInfo(u);
        JSONArray jsonArray = JSONObject.parseObject(rankFundInfo).getJSONObject("showapi_res_body").getJSONObject("pageBean").getJSONArray("contentList");
        List<Fund> fundList = new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            String s = jsonObject1.toString();
            Fund fund = JSON.parseObject(s, Fund.class);
            fund.setType(stockUtil.FundTypeTo(fund.getType()));
            fundList.add(fund);
        }
        return fundList;
    }

    @Override
    public BaseFund detailFund(String code) throws IOException {
        URL u = new URL("http://route.showapi.com/1807-2?showapi_appid="+appId+"&type=&sort=unit_net_value:-1&maxResult=20&page=1&showapi_sign="+key+"&code="+code);
        String rankFundInfo = stockUtil.getStockInfo(u);
        JSONObject jsonObject = JSONObject.parseObject(rankFundInfo).getJSONObject("showapi_res_body").getJSONObject("fund_detail");
        BaseFund baseFund = jsonObject.toJavaObject(BaseFund.class);
        baseFund.setType(stockUtil.FundTypeTo(baseFund.getType()));
        baseFund.setIn_or_out(stockUtil.FundInOutTo(baseFund.getIn_or_out()));
        if(baseFund.getFlow()==null){
            baseFund.setFlow("null");
        }
        return baseFund;
    }

    @Override
    public List<BaseFund> listFund(String code) throws IOException {
        String fundCode="";
        String pinyin="";
        String name="";
        if("code".equals(stockUtil.charJudgment(code))){
            fundCode=code;
        }
        if("english".equals(stockUtil.charJudgment(code))){
            pinyin=code;
        }
        if("chinese".equals(stockUtil.charJudgment(code))){
            name=code;
        }
        URL u = new URL("http://route.showapi.com/1807-8?showapi_appid="+appId+"&showapi_sign="+key+"&name="+name+"&code="+fundCode+"&pinyin="+pinyin);
        String fundInfo = stockUtil.getStockInfo(u);
        JSONArray jsonArray = JSONObject.parseObject(fundInfo).getJSONObject("showapi_res_body").getJSONArray("list");
        List<BaseFund> fundList = new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            String s = jsonObject1.toString();
            BaseFund fund = JSON.parseObject(s, BaseFund.class);
            fund.setType(stockUtil.FundTypeTo(fund.getType()));
            fund.setIn_or_out(stockUtil.FundInOutTo(fund.getIn_or_out()));
            fundList.add(fund);
        }
        return fundList;
    }

    @Override
    public void insertSelfFund(int createId, String fundId) {
        if(fundId == null || fundId.isEmpty() || createId == 0 ){
            return;
        }
        if(fundListMapper.checkPersonalFund(createId,fundId).isEmpty()){
            fundListMapper.insertFundById(createId,fundId);
        }

    }

    @Override
    public void deleteSelfFund(int createId, String fundId) {
        if(fundId == null || fundId.isEmpty() || createId == 0){
            return;
        }
        fundListMapper.deleteFundById(createId,fundId);
    }

    @Override
    public List<PersonalFund> selfFund(int createId) throws IOException {
        List<FundBO> strings = fundListMapper.listPersonalFund(createId);
        List<String> idList = strings.stream().map(FundBO::getFundId).collect(Collectors.toList());
        //将fundId拼接  用，分割
        String stringCode = StringUtils.join(idList.toArray(), ",");
        URL u = new URL("http://route.showapi.com/1807-5?showapi_appid="+appId+"&codes="+stringCode+"&showapi_sign="+key);
        System.out.println(u.toString());
        String fundInfo = stockUtil.getStockInfo(u);
        JSONArray jsonArray = JSONObject.parseObject(fundInfo).getJSONObject("showapi_res_body").getJSONArray("list");
        List<PersonalFund> fundList = new ArrayList<>();
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            String s = jsonObject1.toString();
            PersonalFund fund = JSON.parseObject(s, PersonalFund.class);
            Jsonpgz jsonpgz = moreFundInfo(fund.getCode());
            if(jsonpgz != null) {
                fund.setName(jsonpgz.getName());
                fund.setDwjz(jsonpgz.getDwjz());
                fund.setGsz(jsonpgz.getGsz());
                fund.setGszzl(jsonpgz.getGszzl());
            }else{
                BaseFund baseFund = detailFund(fund.getCode());
                fund.setName(baseFund.getName_short());
            }
            fundList.add(fund);
        }

        return fundList;
    }

    private Jsonpgz moreFundInfo(String code){
        JSONObject jsonObject = null;
        Jsonpgz jsonpgz = null;
        int len=15;
        String u ="http://fundgz.1234567.com.cn/js/"+code+".js?rt=1463558676006";
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> exchange = restTemplate.exchange(u, HttpMethod.GET, null, String.class);
        String body = exchange.getBody();
        System.out.println(body);
        String substring = body.substring(7,body.length()-1);
        substring=substring.replace("(","");
        substring=substring.replace(")","");
        System.out.println(substring);
        if(substring.length()> len) {
            jsonObject = JSONObject.parseObject(substring);
            jsonpgz = jsonObject.toJavaObject(Jsonpgz.class);
        }

        return jsonpgz;
    }


}
