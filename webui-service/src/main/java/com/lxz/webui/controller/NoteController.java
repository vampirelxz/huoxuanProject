package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/17/10:13
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.controller
 * 文件描述: @Description: 随手记控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.GatewayFeign;
import com.lxz.webui.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 包名称： com.lxz.webui.controller
 * 类名称：NoteController
 * 类描述：随手记控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/17/10:13
 */
@Controller
public class NoteController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GatewayFeign gatewayFeign;


    @PostMapping("/saveOrUpdateNote")
    @ResponseBody
    public String saveOrUpdateNote(@RequestParam("createId") String createId, @RequestParam("id") String id, @RequestParam("title") String title, @RequestParam("content") String content ,@RequestParam("token")String token ){
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("createId", createId);
        bodyMap.add("id", id);
        bodyMap.add("title", title);
        bodyMap.add("content", content);
        System.out.println(token);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);

        String url="http://localhost:2001/lifetools/saveOrUpdateNote";
        try{
            restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    @GetMapping("/listNoteInfo")
    public String listNoteInfo(@RequestParam("createId") int createId, Model model,@RequestParam("token")String token ){
        if (createId == 0) {
            return null;
        }
        List<Note> noteList = gatewayFeign.listNoteInfo(createId, token);
        model.addAttribute("notes", noteList);
        return "note::notes";
    }

    @GetMapping("/getContent")
    @ResponseBody
    public Note getContent(@RequestParam("id") int id,@RequestParam("token")String token ){
        if (id == 0) {
            return null;
        }
        Note note = gatewayFeign.getContent(id, token);
        return note;
    }

    @GetMapping("/deleteNote")
    @ResponseBody
    public void deleteNote(@RequestParam("id") int id, @RequestParam("createId") int createId,@RequestParam("token")String token ){
        gatewayFeign.deleteNote(id, createId, token);

    }
}
