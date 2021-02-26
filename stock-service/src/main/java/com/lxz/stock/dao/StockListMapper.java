package com.lxz.stock.dao;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/26/17:04
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.dao
 * 文件描述: @Description: 
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.stock.entity.StockBO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 包名称： com.lxz.stock.dao
 * 类名称：StockListMapper
 * 类描述：
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/26/17:04
 */

@Mapper
public interface StockListMapper {
    /**
     * 查询关注股票
     * @param createId
     * @return
     */
    List<StockBO> listPersonalStock(int createId);
}
