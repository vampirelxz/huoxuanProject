package com.lxz.lifetools.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/21/11:58
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.controller
 * 文件描述: @Description: 垃圾识别控制层
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.lifetools.entity.Garbage;
import com.lxz.lifetools.entity.GarbageRank;
import com.lxz.lifetools.service.GarbageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * 包名称： com.lxz.lifetools.controller
 * 类名称：GarbageController
 * 类描述：垃圾识别控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/21/11:58
 */
@RestController
public class GarbageController {

    @Autowired
    GarbageService garbageService;

    @GetMapping("/garbage")
    List<Garbage> listGarbageInfo(@RequestParam String name){
        return garbageService.garbageSorting(name);
    }

    @GetMapping("/garbageRankTitil")
    String[] garbageRankTitle(){
        List<GarbageRank> garbageRanks = garbageService.garbageRank();
        String[] date=new String[garbageRanks.size()];
        int i=0;
        for(Iterator iterator =garbageRanks.iterator();iterator.hasNext();){

            GarbageRank next = (GarbageRank) iterator.next();
            date[i]=next.getName();
            i++;
        }
        System.out.println(date.toString());
        return date;
    }

    @GetMapping("/garbageRankValue")
    int[] garbageRankValue(){
        List<GarbageRank> garbageRanks = garbageService.garbageRank();
        int[] date=new int[garbageRanks.size()];
        int i=0;
        for(Iterator iterator =garbageRanks.iterator();iterator.hasNext();){

            GarbageRank next = (GarbageRank) iterator.next();
            date[i]=next.getIndex();
            i++;
        }
        System.out.println(date.toString());
        return date;
    }
}

