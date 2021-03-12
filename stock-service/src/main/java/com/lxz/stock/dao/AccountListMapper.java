package com.lxz.stock.dao;

import com.lxz.stock.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/9/16:14
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.dao
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
@Mapper
public interface AccountListMapper {
    void insertAccountById(AccountBO accountBO);

    List<AccountBO> listAccount(int createId);

    void deleteAccountById(int id);

    AccountMonthVO getMonthModel(int createId);

    List<AccountWeekVO> getWeekModel(int createId);

    List<AccountWeekDate> getThisWeekDate(int createId);

    List<AccountWeekDate> getLastWeekDate(int createId);

    List<AccountMonthDate> getThisMonthDate(int createId);

    List<AccountMonthDate> getLastMonthDate(int createId);

    List<AccountPieData> getMonthPie(int createId);

    List<AccountPieData> getYearPie(int createId);
}
