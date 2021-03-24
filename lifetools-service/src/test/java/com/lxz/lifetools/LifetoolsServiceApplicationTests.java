package com.lxz.lifetools;

import com.lxz.lifetools.dao.NoteMapper;
import com.lxz.lifetools.entity.AiReply;
import com.lxz.lifetools.entity.GarbageRank;
import com.lxz.lifetools.entity.ToDoList;
import com.lxz.lifetools.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;


@SpringBootTest
class LifetoolsServiceApplicationTests {

    @Autowired
    ExpressService expressService;
    @Autowired
    GarbageService garbageService;
    @Autowired
    ToDoListService toDoListService;
    @Autowired
    NoteService noteService;
    @Autowired
    NoteMapper noteMapper;
    @Autowired
    AiReplyService aiReplyService;

    @Test
    void contextLoads() {
        System.out.println(expressService.listExpress("JD0038652790309","京东快递").toString());
    }

    @Test
    void testGarbage(){
        System.out.println(garbageService.garbageSorting("sada"));
    }

    @Test
    void testTime() throws ParseException {
        List<ToDoList> toDoLists = toDoListService.listToDoList(10001);
        System.out.println(toDoLists);
    }

    @Test
    void testNoteInsert(){
        noteService.saveOrUpdateNote("100001","10001","测试1","测试1");
//        noteMapper.saveNote(10001,"2021/3/16","测试","测试");
    }

    @Test
    void testGarbageRank(){
        List<GarbageRank> garbageRanks = garbageService.garbageRank();
        System.out.println(garbageRanks);
    }

    @Test
    void testAiReply(){
        AiReply ai = aiReplyService.getReply("你好");
        System.out.println(ai);
    }
}
