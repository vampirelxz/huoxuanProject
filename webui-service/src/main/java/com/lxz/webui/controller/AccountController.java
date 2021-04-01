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
import com.lxz.webui.consumer.api.feign.GatewayFeign;
import com.lxz.webui.entity.AccountBO;
import com.lxz.webui.entity.AccountMonthVO;
import com.lxz.webui.entity.AccountWeekVO;
import com.lxz.webui.entity.Data1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    GatewayFeign gatewayFeign;

    @PostMapping("/insertSelfAccount")
    @ResponseBody
    public String insertSelfAccount(@RequestParam("createId") String createId,@RequestParam("type") String type,@RequestParam("time") String time,@RequestParam("remark") String remark,@RequestParam("money") String money,@RequestParam("token") String token){
        try{
            gatewayFeign.insertSelfAccount(createId, type, time, remark, money, token);
        }catch (Exception e){
            e.printStackTrace();
            return "login.html";
        }
        return null;
    }

    @GetMapping("/account")
    public String listAccount(@RequestParam("createId") int createId, Model model,@RequestParam("token") String token){
        if (createId == 0) {
            return null;
        }
        List<AccountBO> accountBOS;
        try {
            System.out.println("出错");
            accountBOS = gatewayFeign.listAccount(createId, token);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        model.addAttribute("account", accountBOS);
        System.out.println(model.getAttribute("account"));
        return "stock/account::account";
    }

    @GetMapping("/deleteAccount")
    @ResponseBody
    public void delete(@RequestParam("id") int id,@RequestParam("token") String token){
        gatewayFeign.deleteAccount(id,token);

    }

    @GetMapping("/getMonthModel")
    public String getMonthModel(@RequestParam("createId") int createId,Model model,@RequestParam("token") String token){
        AccountMonthVO accountMonthVOS = gatewayFeign.getMonthModel(createId, token);
        model.addAttribute("accordion", accountMonthVOS);
        System.out.println(model.getAttribute("accordion"));
        return "stock/account::accordion";
    }

    @GetMapping("/getWeekModel")
    @ResponseBody
    public String getWeekModel(int createId,Model model,@RequestParam("token") String token){
        List<AccountWeekVO> accountWeekVOS = gatewayFeign.getWeekModel(createId, token);
        String s="[{\"name\":\"暂无数据\",\"value\":1.0},{\"name\":\"暂无数据\",\"value\":1.0},{\"name\":\"暂无数据\",\"value\":1.0},{\"name\":\"暂无数据\",\"value\":1.0}]";
        try {
            List<Data1> data1s = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Data1 data1 = new Data1();
                if (accountWeekVOS.get(i) != null) {
                    data1.setName(accountWeekVOS.get(i).getType());
                    data1.setValue(accountWeekVOS.get(i).getTypeAll());
                    data1s.add(data1);
                }
            }
            s = JSONArray.toJSONString(data1s);
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println(s);

        return s;
    }

    @GetMapping("/getThisWeekData")
    @ResponseBody
    public String getThisWeekData(@RequestParam("createId") int createId,@RequestParam("token") String token){
        String[] thisWeekData = gatewayFeign.getThisWeekData(createId, token);
        String body = JSONArray.toJSONString(thisWeekData);
        System.out.println(body);
        return body;
    }

    @GetMapping("/getLastWeekData")
    @ResponseBody
    public String getLastWeekData(@RequestParam("createId") int createId,@RequestParam("token") String token){
        String body = JSONArray.toJSONString(gatewayFeign.getLastWeekData(createId, token));
        return body;
    }

    @GetMapping("/getThisMonthData")
    @ResponseBody
    public String getThisMonthData(@RequestParam("createId") int createId,@RequestParam("token") String token){
        String body = JSONArray.toJSONString(gatewayFeign.getThisMonthData(createId, token));
        return body;
    }

    @GetMapping("/getLastMonthData")
    @ResponseBody
    public String getLastMonthData(@RequestParam("createId") int createId,@RequestParam("token") String token){
        String body = JSONArray.toJSONString(gatewayFeign.getLastMonthData(createId, token));
        return body;
    }

    @GetMapping("/getMonthTypePie")
    @ResponseBody
    public String getMonthTypePie(@RequestParam("createId") int createId,@RequestParam("token") String token){
        String body = JSONArray.toJSONString(gatewayFeign.getMonthTypePie(createId, token));
        return body;
    }

    @GetMapping("/getMonthValuePie")
    @ResponseBody
    public String getMonthValuePie(@RequestParam("createId") int createId,@RequestParam("token") String token){
        String body = JSONArray.toJSONString(gatewayFeign.getMonthValuePie(createId, token));
        return body;
    }

    @GetMapping("/getYearTypePie")
    @ResponseBody
    public String getYearTypePie(@RequestParam("createId") int createId,@RequestParam("token") String token){
        String body = JSONArray.toJSONString(gatewayFeign.getYearTypePie(createId, token));
        return body;
    }

    @GetMapping("/getYearValuePie")
    @ResponseBody
    public String getYearValuePie(@RequestParam("createId") int createId,@RequestParam("token") String token){
        String body = JSONArray.toJSONString(gatewayFeign.getYearValuePie(createId, token));
        return body;
    }
}
