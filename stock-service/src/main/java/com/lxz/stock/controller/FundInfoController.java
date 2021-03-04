package com.lxz.stock.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/3/13:48
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.controller
 * 文件描述: @Description: 基金控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.stock.entity.BaseFund;
import com.lxz.stock.entity.Fund;
import com.lxz.stock.entity.PersonalFund;
import com.lxz.stock.service.FundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 包名称： com.lxz.stock.controller
 * 类名称：FundInfoController
 * 类描述：基金控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/3/13:48
 */

@RestController
public class FundInfoController {
    @Autowired
    FundInfoService fundInfoService;


    @GetMapping("/rankFund")
    public List<Fund> listRankFund() throws IOException {
        List<Fund> fundList = fundInfoService.rankFund();
        return fundList;
    }

    @GetMapping("/detailFund")
    public BaseFund listDetailFund(@RequestParam("code") String code) throws IOException {
        BaseFund baseFund = fundInfoService.detailFund(code);
        return baseFund;
    }

    @GetMapping("/listFund")
    public List<BaseFund> listFund(@RequestParam("code") String code) throws IOException {
        List<BaseFund> baseFunds = fundInfoService.listFund(code);
        return baseFunds;
    }


    @GetMapping("/selfFund")
    public List<PersonalFund> personalFund(@RequestParam("createId") Integer createId) throws IOException {
        System.out.println(createId);
        List<PersonalFund> personalFunds = fundInfoService.selfFund(createId);
        return personalFunds;
    }

    @PostMapping("/insertSelfFund")
    public void insertSelfFund(@RequestParam("createId") String createId, @RequestParam("fundId") String fundId){
        Integer uid = Integer.valueOf(createId);
        fundInfoService.insertSelfFund(uid,fundId);
    }

    @PostMapping("/deleteSelfFund")
    public void deleteSelfFund(@RequestParam("createId") String createId, @RequestParam("fundId") String fundId){
        Integer uid = Integer.valueOf(createId);
        fundInfoService.deleteSelfFund(uid,fundId);
    }
}
