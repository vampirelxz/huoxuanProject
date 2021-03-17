package com.lxz.lifetools.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/17/10:02
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.controller
 * 文件描述: @Description: 随手记控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.lifetools.entity.Note;
import com.lxz.lifetools.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 包名称： com.lxz.lifetools.controller
 * 类名称：NoteController
 * 类描述：随手记控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/17/10:02
 */
@RestController
public class NoteController {
    @Autowired
    NoteService noteService;

    @PostMapping("/saveOrUpdateNote")
    public void saveOrUpdateNote(@RequestParam("id") String id,@RequestParam("createId") String createId,@RequestParam("title") String title,@RequestParam("content") String content) {
        noteService.saveOrUpdateNote(id,createId,title,content);
    }

    @GetMapping("/listNoteInfo")
    public List<Note> listNoteInfo(int createId){
        List<Note> notes = noteService.listNote(createId);
        System.out.println(notes);
        return notes;
    }

    @GetMapping("/getContent")
    public Note getContent(int id){
        Note content = noteService.getContent(id);
        return content;
    }

    @GetMapping("/deleteNote")
    public void deleteNote(@RequestParam("id") int id,@RequestParam("createId") int createId){
        noteService.deleteNote(id,createId);
    }
}
