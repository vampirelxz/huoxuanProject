package com.lxz.webui.consumer.api.feign;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/23/9:09
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.api.feign
 * 文件描述: @Description: 新闻Feign
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.entity.New;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 包名称： com.lxz.webui.consumer.api.feign
 * 类名称：NewsFeign
 * 类描述：新闻Feign
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/23/9:09
 */
@Component
@FeignClient("news")
public interface NewsFeign {
    @RequestMapping(value = "/listNews",method = RequestMethod.GET)
    List<New> listNews(@RequestParam("name") String name);

    @RequestMapping(value = "/listClassifyNews",method = RequestMethod.GET)
    List<New> listClassifyNews(@RequestParam("name") String name);

    @RequestMapping(value = "/listNewsInfo",method = RequestMethod.GET)
    List<New> listNewsInfo();
}
