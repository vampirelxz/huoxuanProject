package com.lxz.lifetools.service;

import com.lxz.lifetools.entity.Note;

import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/16/17:17
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface NoteService {
    List<Note> listNote(Integer createId);

    void deleteNote(int id,int createId);

    void saveOrUpdateNote(String id,String createId,String title,String content);

    Note getContent(int id);
}
