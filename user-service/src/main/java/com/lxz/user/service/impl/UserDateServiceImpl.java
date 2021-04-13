package com.lxz.user.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/12/11:54
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service.impl
 * 文件描述: @Description: 用户统计数据
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.user.service.UserDateService;
import com.lxz.user.utils.UserDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 包名称： com.lxz.user.service.impl
 * 类名称：UserDateServiceImpl
 * 类描述：用户统计数据
 * 创建人：@author xxxxx
 * 创建时间：2021/4/12/11:54
 */
@Service
public class UserDateServiceImpl implements UserDateService {
    @Autowired
    UserDateUtils userDateUtils;

    @Override
    @Order(1)
    public int onlineUser(String token){
        Integer onlineCount = userDateUtils.getOnlineCount();
        return onlineCount;
    }

    @Override
    public Long useTime(String email) {
        Map<String, List<Date>> timeMap = UserDateUtils.timeMap;
        List<Date> dateList = timeMap.get(email);
        if(dateList == null){
            return Long.valueOf(0);
        }
        System.out.println(dateList);
        Date max = dateList.stream().max(Date::compareTo).get();
        Date min = dateList.stream().min(Date::compareTo).get();
        long time=(max.getTime() - min.getTime()) / (1000 * 60 );
        return time;
    }

    @Override
    @Order(2)
    public int visitUser(String token) {
        int countVisit = UserDateUtils.countVisit;
        return countVisit;
    }
}
