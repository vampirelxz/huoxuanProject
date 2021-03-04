package com.lxz.stock.service;

import com.lxz.stock.entity.BaseFund;
import com.lxz.stock.entity.Fund;
import com.lxz.stock.entity.PersonalFund;

import java.io.IOException;
import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/3/13:49
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface FundInfoService {
    /**
     * @Title: rankFund
     * @Description: 得到基金排行榜
     * @param null
     * @return:   List<Fund>
     * @throws IOException
     * @author: liuxuanzhi
     * @Date:  2021/3/3/13:53
     */
    List<Fund> rankFund() throws IOException;

    /**
     * @Title: detailFund
     * @Description: 详细数据
     * @param code
     * @return:   BaseFund
     * @throws IOException
     * @author: liuxuanzhi
     * @Date:  2021/3/3/16:29
     */
    BaseFund detailFund(String code) throws IOException;

    /**
     * @Title: listFund
     * @Description: 查找基金信息根据代码或名字或拼音
     * @param code
     * @return: listFund
     * @throws IOException
     * @author: liuxuanzhi
     * @Date:  2021/3/4/10:28
     */
    List<BaseFund> listFund(String code) throws  IOException;

    /**
     * @Title: inserSelfFund
     * @Description: 添加关注基金
     * @param createId
     * @param fundId
     * @return:    null
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/4/13:52
     */
    void insertSelfFund(int createId ,String fundId);

    /**
     * @Title: deleteSelfFund
     * @Description: 删除关注基金
     * @param createId
     * @param fundId
     * @return:    null
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/4/13:52
     */
    void deleteSelfFund(int createId, String fundId);

    List<PersonalFund> selfFund(int createId) throws IOException;
}
