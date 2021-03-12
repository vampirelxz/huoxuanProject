package com.lxz.stock.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/9/17:17
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.controller
 * 文件描述: @Description: 账簿控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.stock.entity.AccountBO;
import com.lxz.stock.entity.AccountMonthVO;
import com.lxz.stock.entity.AccountWeekVO;
import com.lxz.stock.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * 包名称： com.lxz.stock.controller
 * 类名称：AccountInfoController
 * 类描述：账簿控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/9/17:17
 */

@RestController
public class AccountInfoController {
    @Autowired
    AccountInfoService accountInfoService;

    @PostMapping("/insertSelfAccount")
    public void insertSelfAccount(@RequestParam("createId") String createId,@RequestParam("type") String type,@RequestParam("time") String time,@RequestParam("remark") String remark,@RequestParam("money") String money) throws ParseException {
        System.out.println(createId+','+time+','+type+','+remark+','+money);
        accountInfoService.insertAccount(createId,type,time,remark,money);
    }

    @GetMapping("/listAccount")
    public List<AccountBO> listAccount(@RequestParam("createId")int createId){
        List<AccountBO> accountBOS = accountInfoService.listAccount(createId);
        return accountBOS;
    }

    @GetMapping("/deleteAccount")
    public void delete(@RequestParam("id") int id){
        if(id == 0){
            return;
        }
        accountInfoService.deleteAccountById(id);
    }

    @GetMapping("/getMonthModel")
    public AccountMonthVO getMonthModel(int createId){
        AccountMonthVO monthModel = accountInfoService.getMonthModel(createId);
        return monthModel;
    }

    @GetMapping("/getWeekModel")
    public List<AccountWeekVO> getWeekModel(int createId){
        List<AccountWeekVO> weekModel = accountInfoService.getWeekModel(createId);
        return weekModel;
    }

    @GetMapping("/getThisWeekData")
    public String[] getThisWeekData(int createId){
        String[] thisWeekDate = accountInfoService.getThisWeekDate(createId);
        return thisWeekDate;
    }

    @GetMapping("/getLastWeekData")
    public String[] getLastWeekData(int createId){
        String[] lastWeekDate = accountInfoService.getLastWeekDate(createId);
        return lastWeekDate;
    }

    @GetMapping("/getThisMonthData")
    public String[] getThisMonthData(int createId){
        String[] thisMonthDate = accountInfoService.getThisMonthDate(createId);
        return thisMonthDate;
    }

    @GetMapping("/getLastMonthData")
    public String[] getLastMonthData(int createId){
        String[] lastMonthDate = accountInfoService.getLastMonthDate(createId);
        return lastMonthDate;
    }

    @GetMapping("/getMonthTypePie")
    public String[] getMonthTypePie(int createId){
        String[] lastMonthDate = accountInfoService.getMonthTypePie(createId);
        return lastMonthDate;
    }

    @GetMapping("/getMonthValuePie")
    public String[] getMonthValuePie(int createId){
        String[] lastMonthDate = accountInfoService.getMonthValuePie(createId);
        return lastMonthDate;
    }

    @GetMapping("/getYearTypePie")
    public String[] getYearTypePie(int createId){
        String[] lastMonthDate = accountInfoService.getYearTypePie(createId);
        return lastMonthDate;
    }

    @GetMapping("/getYearValuePie")
    public String[] getYearValuePie(int createId){
        String[] lastMonthDate = accountInfoService.getYearValuePie(createId);
        return lastMonthDate;
    }
}
