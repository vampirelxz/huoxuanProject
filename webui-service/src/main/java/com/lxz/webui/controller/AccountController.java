package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/9/17:04
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.controller
 * 文件描述: @Description: 账簿控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxz.webui.entity.AccountBO;
import com.lxz.webui.entity.AccountMonthVO;
import com.lxz.webui.entity.AccountWeekVO;
import com.lxz.webui.entity.Data1;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 包名称： com.lxz.webui.controller
 * 类名称：AccountController
 * 类描述：账簿控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/9/17:04
 */
@Controller
public class AccountController {
    @Autowired
    UtilController utilController;
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/insertSelfAccount")
    @ResponseBody
    public String insertSelfAccount(@RequestParam("createId") String createId,@RequestParam("type") String type,@RequestParam("time") String time,@RequestParam("remark") String remark,@RequestParam("money") String money){
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("createId", createId);
        bodyMap.add("type", type);
        bodyMap.add("time", time);
        bodyMap.add("remark", remark);
        bodyMap.add("money", money);
        String token=utilController.t;
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/insertSelfAccount";
        try{
            restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        }catch (Exception e){
            e.printStackTrace();
            return "login.html";
        }
        return null;
    }

    @GetMapping("/account")
    public String listAccount(@RequestParam("createId") int createId, Model model){
        if (createId == 0) {
            return null;
        }
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();
        String token = utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);
        String url = "http://localhost:2001/stock/listAccount?createId=" + createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        System.out.println(body);
        if(body.isEmpty()||body == null){
            return null;
        }
        List<AccountBO> accountBOS = JSONArray.parseArray(body, AccountBO.class);
        model.addAttribute("account", accountBOS);
        return "stock/account::account";
    }

    @GetMapping("/deleteAccount")
    @ResponseBody
    public void delete(@RequestParam("id") int id){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/deleteAccount?id="+id;
        restTemplate.exchange(url, HttpMethod.GET, request, String.class);

    }

    @GetMapping("/getMonthModel")
    public String getMonthModel(int createId,Model model){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getMonthModel?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        AccountMonthVO accountMonthVOS = JSONObject.parseObject(body, AccountMonthVO.class);
        model.addAttribute("accordion", accountMonthVOS);
        System.out.println(model.getAttribute("accordion"));
        return "stock/account::accordion";
    }

    @GetMapping("/getWeekModel")
    @ResponseBody
    public String getWeekModel(int createId,Model model){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getWeekModel?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        List<AccountWeekVO> accountWeekVOS = JSONArray.parseArray(body, AccountWeekVO.class);

        List<Data1> data1s=new ArrayList<>();
        for(int i=0;i<4;i++) {
            Data1 data1 = new Data1();
            data1.setName(accountWeekVOS.get(i).getType());
            data1.setValue(accountWeekVOS.get(i).getTypeAll());
            data1s.add(data1);
        }
        String s = JSONArray.toJSONString(data1s);

        System.out.println(s);

        return s;
    }

    @GetMapping("/getThisWeekData")
    @ResponseBody
    public String getThisWeekData(@RequestParam("createId") int createId){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getThisWeekData?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        return body;
    }

    @GetMapping("/getLastWeekData")
    @ResponseBody
    public String getLastWeekData(@RequestParam("createId") int createId){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getLastWeekData?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        return body;
    }

    @GetMapping("/getThisMonthData")
    @ResponseBody
    public String getThisMonthData(@RequestParam("createId") int createId){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getThisMonthData?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        return body;
    }

    @GetMapping("/getLastMonthData")
    @ResponseBody
    public String getLastMonthData(@RequestParam("createId") int createId){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getLastMonthData?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        return body;
    }

    @GetMapping("/getMonthTypePie")
    @ResponseBody
    public String getMonthTypePie(@RequestParam("createId") int createId){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getMonthTypePie?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        return body;
    }

    @GetMapping("/getMonthValuePie")
    @ResponseBody
    public String getMonthValuePie(@RequestParam("createId") int createId){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getMonthValuePie?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        return body;
    }

    @GetMapping("/getYearTypePie")
    @ResponseBody
    public String getYearTypePie(@RequestParam("createId") int createId){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getYearTypePie?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        return body;
    }

    @GetMapping("/getYearValuePie")
    @ResponseBody
    public String getYearValuePie(@RequestParam("createId") int createId){
        MultiValueMap<String, Integer> bodyMap = new LinkedMultiValueMap<>();

        String token=utilController.t;
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/getYearValuePie?createId="+createId;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        return body;
    }
}
