package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/25/17:30
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.controller
 * 文件描述: @Description: 股票前端控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSONArray;
import com.lxz.webui.consumer.api.feign.StockFeign;
import com.lxz.webui.entity.BaseStock;
import com.lxz.webui.entity.BuyStock;
import com.lxz.webui.entity.PersonalStock;
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
 * 包名称： com.lxz.webui.consumer.controller
 * 类名称：StockController
 * 类描述：股票前端控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/25/17:30
 */

@Controller
public class StockController {
    @Autowired
    private StockFeign stockFeign;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilController utilController;

    @GetMapping("/realtimeStock")
    public String listRealTimeStock(@RequestParam String code, Model model) {

        if (code == null) {
            code = "300377";
        }
        BuyStock buyStock = stockFeign.listRealTimeStock(code);
        System.out.println(buyStock.getCode());
        model.addAttribute("timeStock", buyStock);
        System.out.println(model.getAttribute("timeStock"));
        return "stock/stock::timeStock";
    }


    @GetMapping("stock")
    public String login(Model model) throws IOException {
        List<BaseStock> baseStocks = stockFeign.baseStock();
        try {
            baseStocks = stockFeign.baseStock();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("baseStock0", baseStocks.get(0));
        model.addAttribute("baseStock1", baseStocks.get(1));
        model.addAttribute("baseStock2", baseStocks.get(2));
        model.addAttribute("baseStock3", baseStocks.get(3));
        model.addAttribute("baseStock4", baseStocks.get(4));
        System.out.println(model.getAttribute("baseStock0"));
        return "stock/stock::baseStock";
    }

    @GetMapping("/selfStock")
    public String listSelfStock(@RequestParam("createId") String createId, Model model) {
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
        String url = "http://localhost:2001/stock/selfStock?createId=" + uid;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        System.out.println(body);
        List<PersonalStock> personalStocks = JSONArray.parseArray(body, PersonalStock.class);
        model.addAttribute("selfStock", personalStocks);
        return "stock/stock::selfStock";
    }

    @PostMapping("/insertSelfStock")
    @ResponseBody
    public String insertSelfStock(@RequestParam("createId") String createId, @RequestParam("stockId") String stockId) {
        System.out.println(createId);
        System.out.println(stockId);

        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("stockId", stockId);
        bodyMap.add("createId", createId);
        String token=utilController.t;
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/insertSelfStock";
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


    @PostMapping("/deleteSelfStock")
    @ResponseBody
    public String delete(@RequestParam("createId") String createId, @RequestParam("stockId") String stockId){
        System.out.println(createId);
        System.out.println(stockId);

        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("stockId", stockId);
        bodyMap.add("createId", createId);
        String token=utilController.t;
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/stock/deleteSelfStock";
        try{
            restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        }catch (Exception e){
            e.printStackTrace();
            return "localhost:80/login.html";
        }


        return "localhost:80/index";
    }
}
