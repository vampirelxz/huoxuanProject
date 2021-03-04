package com.lxz.stock.dao;

import com.lxz.stock.entity.FundBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/3/13:48
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.dao
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
@Mapper
public interface FundListMapper {
    /**
     * 查询关注基金
     * @param createId
     * @return
     */
    List<FundBO> listPersonalFund(int createId);

    /**
     * 删除关注基金
     * @param createId
     * @param fundId
     * @return
     */
    void deleteFundById(@Param("createId")int createId, @Param("fundId") String fundId);

    /**
     * 新增关注基金
     * @param createId
     * @param fundId
     * @return
     */
    void insertFundById(@Param("createId") int createId, @Param("fundId") String fundId);

    List<FundBO> checkPersonalFund(@Param("createId") int createId, @Param("fundId") String fundId);
}
