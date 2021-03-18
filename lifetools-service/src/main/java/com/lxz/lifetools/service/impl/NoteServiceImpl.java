package com.lxz.lifetools.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/16/17:26
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.service.impl
 * 文件描述: @Description: 笔记服务实现类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.lifetools.dao.NoteMapper;
import com.lxz.lifetools.entity.Note;
import com.lxz.lifetools.service.NoteService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 包名称： com.lxz.lifetools.service.impl
 * 类名称：NoteServiceImpl
 * 类描述：笔记服务实现类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/16/17:26
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Resource
    NoteMapper noteMapper;

    @Override
    public List<Note> listNote(Integer createId) {
        if(createId == null){
            return null;
        }
        List<Note> notes = noteMapper.listNoteInfo(createId);

        return notes;
    }

    @Override
    public void deleteNote(@Param("id") int id,@Param("createId") int createId) {
        noteMapper.deleteNote(id,createId);
    }

    @Override
    public void saveOrUpdateNote(String id, String createId, String title, String content) {
        if(title == null){
            return;
        }
        int i = Integer.parseInt(id);
        int i1 = Integer.parseInt(createId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(new Date());
        System.out.println(date);
        if(i == 0 || id==null || noteMapper.isExist(i) ==null ){
            System.out.println(id+","+createId);
            noteMapper.saveNote(i1,date,title,content);
        }else{
            if(noteMapper.isExist(i) != null) {
                noteMapper.updateNote(i, date, title, content);
            }else {
                System.out.println("添加失败");
                return;
            }

        }
    }

    @Override
    public Note getContent(int id) {
        Note content = noteMapper.getContent(id);
        return content;
    }
}
