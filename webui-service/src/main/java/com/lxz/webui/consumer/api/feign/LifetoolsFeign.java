package com.lxz.webui.consumer.api.feign;

import com.lxz.webui.entity.ToDoList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/8/11:11
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.api.feign
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
@Component
@FeignClient("lifetools")
public interface LifetoolsFeign {
    @RequestMapping(value = "/toDoList",method = RequestMethod.GET)
    List<ToDoList> toDoList(@RequestParam("createId") int createId);

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    int save(@RequestParam("createId") int createId,@RequestParam("information") String information,@RequestParam("endTime") String endTime);
}
