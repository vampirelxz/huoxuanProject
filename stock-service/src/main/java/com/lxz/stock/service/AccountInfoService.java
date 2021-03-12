package com.lxz.stock.service;

import com.lxz.stock.entity.AccountBO;
import com.lxz.stock.entity.AccountMonthVO;
import com.lxz.stock.entity.AccountWeekVO;

import java.text.ParseException;
import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/9/16:17
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface AccountInfoService {
    /**
     * @Title: insertAccount
     * @Description: 添加个人账簿
     * @param createId
     * @param type
     * @param time
     * @param remark
     * @param money
     * @return: void
     * @throws ParseException
     * @author: liuxuanzhi
     * @Date:  2021/3/9/16:18
     */
    void insertAccount(String createId, String type,  String time, String remark,  String money) throws ParseException;

    /**
     * @Title: listAccount
     * @Description: 查询所有账簿
     * @param createId
     * @return:  List<AccountBO>
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/10/15:49
     */
    List<AccountBO> listAccount(int createId);

    /**
     * @Title: deleteAccountById
     * @Description: 删除
     * @param id
     * @return:    void
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/10/15:50
     */
    void deleteAccountById(int id);

    /**
     * @Title: getMonthModel
     * @Description: 获得月模块
     * @param createId
     * @return:   AccountMonthVO
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/11/11:14
     */
    AccountMonthVO getMonthModel(int createId);

    /**
     * @Title:  getWeekModel
     * @Description: 获得周模块
     * @param createId
     * @return:   List<AccountWeekVO>
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/11/14:29
     */
    List<AccountWeekVO> getWeekModel(int createId);

    /**
     * @Title: getThisWeekDate
     * @Description: 两周数据比较之本周数据
     * @param createId
     * @return:    String[]
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/12/14:20
     */
    String[] getThisWeekDate(int createId);

    /**
     * @Title: getLastWeekDate
     * @Description: 两周数据比较之上周数据
     * @param createId
     * @return:    String[]
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/12/14:20
     */
    String[] getLastWeekDate(int createId);

    /**
     * @Title: getThisMonthDate
     * @Description: 两月比较之本月
     * @param createId
     * @return:   String[]
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/12/17:20
     */
    String[] getThisMonthDate(int createId);

    /**
     * @Title: getLastMonthDate
     * @Description: 两月比较之上个月
     * @param createId
     * @return:    String[]
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/12/17:21
     */
    String[] getLastMonthDate(int createId);

    String[] getMonthTypePie(int createId);

    String[] getMonthValuePie(int createId);

    String[] getYearTypePie(int createId);

    String[] getYearValuePie(int createId);
}
