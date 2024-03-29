package com.lxz.forecast.dao;/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/26/13:37
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.dao
 * 文件描述: @Description: 
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.forecast.entity.AlgorithmInfo;
import com.lxz.forecast.entity.AlgorithmUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 包名称： com.lxz.forecast.dao
 * 类名称：AlgorithmMapper
 * 类描述：
 * 创建人：@author xxxxx
 * 创建时间：2021/3/26/13:37
 */
@Mapper
public interface AlgorithmMapper {
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
    void insertAlgorithmUser(@Param("userId") Integer userId,@Param("algorithmId") Integer algorithmId,@Param("content") String content,@Param("updateTime") String updateTime,@Param("timeExpend") String timeExpend,@Param("spaceExpend") String spaceExpend);

    /**
     * 判断之前是否有做过该题目
     * @param userId
     * @param algorithmId
     * @return
     */
    AlgorithmUser isExistAlgorithmUser(@Param("userId") Integer userId,@Param("algorithmId") Integer algorithmId);

    /**
     *  更新用户对某一道算法的内容
     * @param id
     * @param content
     * @param updateTime
     * @param timeExpend
     * @param spaceExpend
     */
    void updateAlgorithmUser(@Param("id") Integer id,@Param("content") String content,@Param("updateTime") String updateTime,@Param("timeExpend") String timeExpend,@Param("spaceExpend") String spaceExpend);

    /**
     * 通过用户记录表id获取相关信息
     * @param AlgorithmUserId
     * @return
     */
    AlgorithmUser getAlgorithmUser(Integer AlgorithmUserId);
}
