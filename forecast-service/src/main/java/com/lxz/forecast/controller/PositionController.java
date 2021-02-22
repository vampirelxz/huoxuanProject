package com.lxz.forecast.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/20/15:16
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.controller
 * 文件描述: @Description: 定位
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.forecast.utils.PositionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名称： com.lxz.forecast.controller
 * 类名称：PositionController
 * 类描述：定位
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/20/15:16
 */
@RestController
public class PositionController {
    @Autowired
    PositionUtil positionUtil;

    @GetMapping("/getCity")
    public String getCity(){
        return positionUtil.getCity();
    }
}
