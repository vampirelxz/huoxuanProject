package com.lxz.forecast.service;

import com.lxz.forecast.entity.AlgorithmInfo;
import com.lxz.forecast.entity.AlgorithmUser;

import java.util.List;

/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/25/13:36
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface ExecuteStringSourceService {
    /**
     * 执行器游客模式
     * @param source
     * @param systemIn
     * @return
     */
    String execute(String source, String systemIn);

    /**
     * 执行器用户模式
     * @param source
     * @param systemIn
     * @param userId
     * @param algorithmId
     * @return
     */
    String execute2(String source, String systemIn, String userId,String algorithmId);

    /**
     * 查询所有题目
     * @return
     */
    List<AlgorithmInfo> listAlgorithmInfo();

    /**
     * 查询题目的具体信息
     * @param id
     * @return
     */
    AlgorithmInfo getAlgorithmInfo(String id);

    /**
     * 添加用户算法记录
     * @param userId
     * @param algorithmId
     * @param content
     * @param updateTime
     * @param timeExpend
     * @param spaceExpend
     */
    void insertAlgorithmUser(Integer userId,Integer algorithmId,String content,String updateTime,String timeExpend,String spaceExpend);

    /**
     *  更新用户对某一道算法的内容
     * @param algorithmUserId
     * @param content
     * @param updateTime
     * @param timeExpend
     * @param spaceExpend
     */
    void updateAlgorithmUser(Integer algorithmUserId,String content,String updateTime,String timeExpend,String spaceExpend);

    /**
     * 判断之前是否有做过该题目
     * @param userId
     * @param algorithmId
     * @return
     */
    Integer isExistAlgorithmUser(Integer userId,Integer algorithmId);

    /**
     * 通过用户记录表id获取相关信息
     * @param algorithmUserid
     * @return
     */
    AlgorithmUser getAlgorithmUser(Integer algorithmUserid);
}
