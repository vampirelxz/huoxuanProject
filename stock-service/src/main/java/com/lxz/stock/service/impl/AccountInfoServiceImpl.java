package com.lxz.stock.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/9/16:21
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.service.impl
 * 文件描述: @Description: 添加账簿实现类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.stock.dao.AccountListMapper;
import com.lxz.stock.entity.*;
import com.lxz.stock.service.AccountInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 包名称： com.lxz.stock.service.impl
 * 类名称：AccountInfoServiceImpl
 * 类描述：添加账簿实现类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/9/16:21
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    @Resource
    AccountListMapper accountListMapper;

    @Override
    public void insertAccount( String createId, String type,  String time, String remark,  String money) throws ParseException {
        AccountBO accountBO = new AccountBO();
        accountBO.setCreateId(Integer.parseInt(createId));
        accountBO.setType(type);
        accountBO.setRemark(remark);
        accountBO.setMoney(Float.parseFloat(money));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = simpleDateFormat.parse(time);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        String time2=simpleDateFormat1.format(date);
        accountBO.setTime(time2);
        accountListMapper.insertAccountById(accountBO);
    }

    @Override
    public List<AccountBO> listAccount(int createId) {
        List<AccountBO> accountBOS = accountListMapper.listAccount(createId);
        return accountBOS;
    }

    @Override
    public void deleteAccountById(int id) {
        accountListMapper.deleteAccountById(id);
    }

    @Override
    public AccountMonthVO getMonthModel(int createId) {
        AccountMonthVO monthModel = accountListMapper.getMonthModel(createId);
        monthModel.setBalance(monthModel.getAllIn()+monthModel.getAllOut());
        if(monthModel.getAllIn().equals(null)){
            monthModel.setAllIn(0.00f);
        }
        if(monthModel.getAllOut() == null){
            monthModel.setAllOut(0.00f);
        }
        if(monthModel.getMonthIn()== null){
            monthModel.setMonthIn(0.00f);
        }
        if(monthModel.getMonthOut() == null){
            monthModel.setMonthOut(0.00f);
        }
        return monthModel;
    }

    @Override
    public List<AccountWeekVO> getWeekModel(int createId) {
        List<AccountWeekVO> weekModel = accountListMapper.getWeekModel(createId);
        for(Iterator iterator = weekModel.iterator(); iterator.hasNext();){
            AccountWeekVO next = (AccountWeekVO) iterator.next();
            next.setProportion((int) Math.ceil(next.getTypeAll()/next.getWeekAll()*100));
        }
        return weekModel;
    }

    @Override
    public String[] getThisWeekDate(int createId) {
        String[] date=new String[7];
        List<AccountWeekDate> thisWeekDate = accountListMapper.getThisWeekDate(createId);
        for(int i=0;i < date.length;i++){
            date[i] = thisWeekDate.get(i).getValue();
        }
        return date;
    }

    @Override
    public String[] getLastWeekDate(int createId) {
        String[] date=new String[7];
        List<AccountWeekDate> lastWeekDate = accountListMapper.getLastWeekDate(createId);
        for(int i=0;i < date.length;i++){
            date[i] = lastWeekDate.get(i).getValue();
        }
        return date;
    }

    @Override
    public String[] getThisMonthDate(int createId) {
        String[] date=new String[8];
        List<AccountMonthDate> thisMonthDate = accountListMapper.getThisMonthDate(createId);
        for(int i=0;i < date.length;i++){
            date[i] = thisMonthDate.get(i).getValue();
        }
        return date;
    }

    @Override
    public String[] getLastMonthDate(int createId) {
        String[] date=new String[8];
        List<AccountMonthDate> lastMonthDate = accountListMapper.getLastMonthDate(createId);
        for(int i=0;i < date.length;i++){
            date[i] = lastMonthDate.get(i).getValue();
        }
        return date;
    }

    @Override
    public String[] getMonthTypePie(int createId) {
        String[] date=new String[6];
        List<AccountPieData> monthPie = accountListMapper.getMonthPie(createId);
        for(int i=0;i < date.length;i++){
            if(monthPie.get(i)==null){
                return date;
            }
            date[i] = monthPie.get(i).getType();
        }
        return date;
    }

    @Override
    public String[] getMonthValuePie(int createId) {
        String[] date=new String[6];
        List<AccountPieData> monthPie = accountListMapper.getMonthPie(createId);
        for(int i=0;i < date.length;i++){
            if(monthPie.get(i)==null){
                return date;
            }
            date[i] = monthPie.get(i).getValue();
        }
        return date;
    }

    @Override
    public String[] getYearTypePie(int createId) {
        String[] date=new String[6];
        List<AccountPieData> yearPie = accountListMapper.getYearPie(createId);
        for(int i=0;i < date.length;i++){
            if(yearPie.get(i)==null){
                return date;
            }
            date[i] = yearPie.get(i).getType();
        }
        return date;
    }

    @Override
    public String[] getYearValuePie(int createId) {
        String[] date=new String[6];
        List<AccountPieData> yearPie = accountListMapper.getYearPie(createId);
        for(int i=0;i < date.length;i++){
            if(yearPie.get(i)==null){
                return date;
            }
            date[i] = yearPie.get(i).getValue();
        }
        return date;
    }
}
