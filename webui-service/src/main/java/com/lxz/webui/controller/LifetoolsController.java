package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/1/16:12
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.controller
 * 文件描述: @Description: 调用每日清单接口
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.LifetoolsFeign;
import com.lxz.webui.entity.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 包名称： com.lxz.webui.consumer.controller
 * 类名称：ToDoListController
 * 类描述：调用每日清单接口
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/1/16:12
 */

@RestController
public class LifetoolsController {

    @Autowired(required = false)
    private LifetoolsFeign lifetoolsFeign;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilController utilController;
/**
 * @Title:
 * @Description:
 *
 * @param
 * @return:
 * @throws
 * @author: liuxuanzhi
 * @Date:  2021/2/1/16:13
 */
    public List<ToDoList> feign(int uid){
        List<ToDoList> toDoLists;
        try {
            toDoLists = lifetoolsFeign.toDoList(uid);
        }catch (Exception e1){
            toDoLists=null;
            e1.printStackTrace();
        }
        return toDoLists;
    }

    @PostMapping("/saveInfo")
    public String save(@RequestParam("information") String information,@RequestParam("endTime") String endTime ,@RequestParam("createId") String createId){
        System.out.println(createId);
        System.out.println(endTime);
        System.out.println(information);
        endTime=endTime.replace('T',' ');
        endTime=endTime+":00";
       // lifetoolsFeign.save(uid, information, endTime);

        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("information", information);
        bodyMap.add("endTime", endTime);
        bodyMap.add("createId", createId);
        String token=utilController.t;
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/lifetools/save";
        try{
            restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        }catch (Exception e){
            e.printStackTrace();
            return "localhost:80/login.html";
        }


        return "localhost:80/index";
    }

//    public Weather getWeather() {
//        String url="https://restapi.amap.com/v3/weather/weatherInfo?city="+positionUtil.getCity()+"&key="+key+"&extensions=base";
//        //String json =restTemplate.getForObject(url,Object.class);
//        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//        JSONArray lives = JSONObject.parseObject(results.getBody()).getJSONArray("lives");
//        return jsonUtil.jsonToObject(lives,Weather.class);
//    }
}
