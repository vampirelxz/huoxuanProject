package com.lxz.lifetools.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/18/17:02
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.controller
 * 文件描述: @Description: 快递控制层
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.lifetools.entity.Express;
import com.lxz.lifetools.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名称： com.lxz.lifetools.controller
 * 类名称：ExpressController
 * 类描述：快递控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/18/17:02
 */

@RestController
public class ExpressController {
    @Autowired
    ExpressService expressService;

    @GetMapping("/express")
    public Express getexpress(@RequestParam String com,String num){
        Express express = expressService.listExpress(num, com);
        return express;
    }

}
