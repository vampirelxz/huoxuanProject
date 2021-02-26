package com.lxz.webui.consumer.api.feign;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/23/9:09
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.api.feign
 * 文件描述: @Description: 股票Feign
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.entity.BaseStock;
import com.lxz.webui.entity.BuyStock;
import com.lxz.webui.entity.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

/**
 * 包名称： com.lxz.webui.consumer.api.feign
 * 类名称：StockFeign
 * 类描述：股票Feign
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/23/9:09
 */
@Component
@FeignClient("stock")
public interface StockFeign {
    @RequestMapping(value = "/realtimeStock",method = RequestMethod.GET)
    BuyStock listRealTimeStock(@RequestParam("code") String code);

    @RequestMapping(value = "/todayStock",method = RequestMethod.GET)
    Stock listTodayStock(@RequestParam("code") String code);

    @RequestMapping(value = "/baseStock",method = RequestMethod.GET)
    List<BaseStock> baseStock() throws IOException ;
}
