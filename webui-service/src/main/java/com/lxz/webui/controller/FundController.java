package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/3/15:06
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.controller
 * 文件描述: @Description: 调用接口控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSONArray;
import com.lxz.webui.consumer.api.feign.StockFeign;
import com.lxz.webui.entity.BaseFund;
import com.lxz.webui.entity.Fund;
import com.lxz.webui.entity.PersonalFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * 包名称： com.lxz.webui.controller
 * 类名称：FundController
 * 类描述：调用接口控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/3/15:06
 */

@Controller
public class FundController {
    @Autowired
    StockFeign stockFeign;
    @Autowired
    UtilController utilController;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/rankFund")
    public String  listRankList(Model model) throws IOException {
        List<Fund> fundList = stockFeign.listRankFund();
        model.addAttribute("rankFund",fundList);
        Object rankFund = model.getAttribute("rankFund");
        System.out.println(rankFund.toString());
        return "stock/fund::rankFund";
    }

    @GetMapping("/detailFund")
    public String listDetailFund(@RequestParam("code") String code,Model model) throws IOException {
        BaseFund baseFund = stockFeign.listDetailFund(code);
        model.addAttribute("detailFund",baseFund);
        System.out.println(model.getAttribute("detailFund"));
        return "stock/fund::detailFund";
    }

    @GetMapping("/listFund")
    public String listFund(@RequestParam("code") String code,Model model) throws IOException {
        List<BaseFund> baseFunds = stockFeign.listFund(code);
        model.addAttribute("listFund",baseFunds);
        System.out.println(model.getAttribute("listFund"));
        return "stock/fund::listFund";
    }

    @GetMapping("/selfFund")
    public String listSelfFund(@RequestParam("createId") String createId, Model model) {
        System.out.println(createId);
        Integer uid = Integer.valueOf(createId);
        if (createId == null) {
            return null;
        }
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();
        String token = utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);
        String url = "http://localhost:2001/stock/selfFund?createId=" + uid;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        System.out.println(body);
        List<PersonalFund> personalFunds = JSONArray.parseArray(body, PersonalFund.class);
        model.addAttribute("selfFund", personalFunds);
        return "stock/fund::selfFund";
    }

    @PostMapping("/insertSelfFund")
    @ResponseBody
    public String insertSelfFund(@RequestParam("createId") String createId, @RequestParam("fundId") String fundId) {
        System.out.println(createId);
        System.out.println(fundId);

        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("fundId", fundId);
        bodyMap.add("createId", createId);
        String token=utilController.t;
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/insertSelfFund";
        try{
            restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("1");
            return "false";
        }
        System.out.println("2");
        return "success";


    }


    @PostMapping("/deleteSelfFund")
    @ResponseBody
    public String delete(@RequestParam("createId") String createId, @RequestParam("fundId") String fundId){
        System.out.println(createId);
        System.out.println(fundId);

        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("fundId", fundId);
        bodyMap.add("createId", createId);
        String token=utilController.t;
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/deleteSelfFund";
        try{
            restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        }catch (Exception e){
            e.printStackTrace();
            return "localhost:80/login.html";
        }


        return "localhost:80/index";
    }
}
