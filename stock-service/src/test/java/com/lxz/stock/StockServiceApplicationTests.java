package com.lxz.stock;

import com.lxz.stock.controller.StockInfoController;
import com.lxz.stock.dao.StockListMapper;
import com.lxz.stock.entity.*;
import com.lxz.stock.service.AccountInfoService;
import com.lxz.stock.service.FundInfoService;
import com.lxz.stock.service.StockInfoService;
import com.lxz.stock.utils.StockUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
class StockServiceApplicationTests {
    @Autowired
    StockInfoController stockInfoController;
    @Autowired
    StockInfoService stockInfoService;
    @Autowired
    StockListMapper stockListMapper;
    @Autowired
    StockUtil stockUtil;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    FundInfoService fundInfoService;
//    @Resource
//    AccountBO accountBO;
    @Autowired
    AccountInfoService accountInfoService;

    @Test
    void contextLoads() {
        stockInfoController.listRealTimeStock("300378");
    }

    @Test
    void contextLoads2() {
        stockInfoController.listTodayStock("300378");
    }

    @Test
    void personalStock() throws IOException {
        List<PersonalStock> stocks = stockInfoService.selfStock(10001);
        System.out.println(stocks);
    }

    @Test
    void personalDelete(){
        stockInfoService.deleteSelfStock(10001,"300377");
    }

    @Test
    void personalInsert(){
        stockInfoService.insertSelfStock(10001,"300377");
    }

    @Test
    void checkPersonStock(){
        List<StockBO> stockBOS = stockListMapper.checkPersonalStock(10002, "300377");
        System.out.println(stockBOS);
    }

    @Test
    void TestFundUrl() {
        String u ="http://fundgz.1234567.com.cn/js/110011.js?rt=1463558676006";
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> exchange = restTemplate.exchange(u, HttpMethod.GET, null, String.class);
        String body = exchange.getBody();
        System.out.println(body);
        String substring = body.substring(7,body.length()-1);
        substring=substring.replace("(","");
        substring=substring.replace(")","");
        System.out.println(substring);
    }

    @Test
    void TestRankFund() throws IOException {
        List<Fund> fundList = fundInfoService.rankFund();
        System.out.println(fundList.toArray().toString());
    }

    @Test
    void TestDetailFund() throws IOException {
        BaseFund baseFund = fundInfoService.detailFund("110011");
        System.out.println(baseFund.toString());
    }

    @Test
    void TestChar(){
        System.out.println(stockUtil.charJudgment("110011"));
        System.out.println(stockUtil.charJudgment("yifanda"));
        System.out.println(stockUtil.charJudgment("易方达"));
    }

    @Test
    void TestInfoFund() throws IOException {
        List<BaseFund> baseFunds = fundInfoService.listFund("110011");
        System.out.println(baseFunds.toString());
    }

    @Test
    void TestSelfFund() throws IOException {
        List<PersonalFund> personalFunds = fundInfoService.selfFund(10001);
        System.out.println(personalFunds);
    }



    @Test
    void TestInsertAccount(){
        AccountBO accountBO = new AccountBO();
        accountBO.setCreateId(10001);
        accountBO.setMoney(79.50f);
        accountBO.setRemark("高铁费");
        accountBO.setTime("2021/03/01");
        accountBO.setType("交通出行");
//        accountInfoService.insertAccount(accountBO);
    }

    @Test
    void TestMonthModel(){
        AccountMonthVO monthModel = accountInfoService.getMonthModel(10001);
        System.out.println(monthModel);
    }

    @Test
    void TestWeekModel(){
        List<AccountWeekVO> weekModel = accountInfoService.getWeekModel(10001);
        System.out.println(weekModel);
    }

    @Test
    void TestGetThisWeekDate(){
        String[] thisWeekDate = accountInfoService.getThisWeekDate(10001);
        System.out.println(thisWeekDate);
    }
}
