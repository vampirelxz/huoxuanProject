package com.lxz.news.service;

import com.lxz.news.entity.ClassifyNew;
import com.lxz.news.entity.New;
import org.springframework.stereotype.Service;

import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/15/13:26
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.news.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

@Service
public interface NewsInfoService {
    /**
     *  listNewInfo
     * @Description: 综合查找
     *
     * @param type
     * @return:   List<New>
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/1/15/13:29
     */
    List<New> listNewsInfo(String type);

    /**
     *  listNewInfo
     * @Description: 综合查找
     *
     * @param
     * @return:   List<New>
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/1/15/13:29
     */
    List<New> listNewsInfo();

    List<ClassifyNew> listNewByClassify(String channel);

}
