package com.lxz.lifetools;

import com.lxz.lifetools.entity.ToDoList;
import com.lxz.lifetools.service.ExpressService;
import com.lxz.lifetools.service.GarbageService;
import com.lxz.lifetools.service.ToDoListService;
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

    @Test
    void contextLoads() {
        System.out.println(expressService.listExpress("ESL83584691743223",null).toString());
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
}
