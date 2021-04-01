package com.lxz.webui.consumer.api.feign;

import com.lxz.webui.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/31/14:06
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.api.feign
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
@Component
@FeignClient("gateway")
public interface GatewayFeign {
    /**
     *  每日一算代码编译
     * @param source 源代码
     * @param systemIn 输入代码
     * @param algorithmId 题目编号
     * @param userId 用户id
     * @param token 秘钥
     * @return String
     */
    @PostMapping("/forecast/run")
    String runCode(@RequestParam("source") String source,
                   @RequestParam("systemIn") String systemIn,@RequestParam("algorithmId") String algorithmId,@RequestParam("userId") String userId,
                   @RequestHeader(name = "Authorization") String token);

    /**
     * 每日一算历史数据
     * @param userId 用户id
     * @param algorithmId 题目编号
     * @param token 秘钥
     * @return AlgorithmUser
     */
    @RequestMapping(value = "/forecast/getAlgorithmUser",method = RequestMethod.GET)
    AlgorithmUser getAlgorithmUser(@RequestParam("userId") Integer userId, @RequestParam("algorithmId") Integer algorithmId,
                                   @RequestHeader(name = "Authorization") String token);

    /**
     * 每日一算题解
     * @param id 题目编号
     * @param token 秘钥
     * @return AlgorithmInfo
     */
    @RequestMapping(value = "/forecast/getAlgorithmInfo",method = RequestMethod.GET)
    AlgorithmInfo getAlgorithmInfo(@RequestParam("id") Integer id,
                                   @RequestHeader("Authorization") String token);

    /**
     * 股票关注股票
     * @param createId 用户id
     * @param token 秘钥
     * @return  List<PersonalStock>
     */
    @RequestMapping(value = "/stock/selfStock",method = RequestMethod.GET)
    List<PersonalStock> listSelfStock(@RequestParam("createId") Integer createId,
                                      @RequestHeader("Authorization") String token);

    /**
     * 股票关注股票添加
     * @param createId 创建id
     * @param stockId 股票编号
     * @param token 秘钥
     */
    @RequestMapping(value = "/stock/insertSelfStock",method = RequestMethod.POST)
    void insertSelfStock( @RequestParam("createId") String createId, @RequestParam("stockId") String stockId,
                                 @RequestHeader("Authorization") String token);

    /**
     * 股票关注股票删除
     * @param createId 用户id
     * @param stockId 股票id
     * @param token 秘钥
     */
    @RequestMapping(value = "/stock/deleteSelfStock",method = RequestMethod.POST)
    void deleteSelfStock( @RequestParam("createId") String createId, @RequestParam("stockId") String stockId,
                          @RequestHeader("Authorization") String token);


    /**
     * 基金关注基金
     * @param createId 用户id
     * @param token 秘钥
     * @return List<PersonalFund>
     */
    @RequestMapping(value = "/stock/selfFund",method = RequestMethod.GET)
    List<PersonalFund> listSelfFund(@RequestParam("createId") Integer createId,
                                    @RequestHeader("Authorization") String token);

    /**
     * 基金关注基金添加
     * @param createId 创建id
     * @param fundId 基金编号
     * @param token 秘钥
     */
    @RequestMapping(value = "/stock/insertSelfFund",method = RequestMethod.POST)
    void insertSelfFund( @RequestParam("createId") String createId, @RequestParam("fundId") String fundId,
                          @RequestHeader("Authorization") String token);

    /**
     * 基金关注基金删除
     * @param createId 用户ID
     * @param fundId 基金编号
     * @param token 秘钥
     */
    @RequestMapping(value = "/stock/deleteSelfFund",method = RequestMethod.POST)
    void deleteSelfFund( @RequestParam("createId") String createId, @RequestParam("fundId") String fundId,
                          @RequestHeader("Authorization") String token);

    /**
     * 账簿插入数据
     * @param createId 用户id
     * @param type 类型
     * @param time 时间
     * @param remark 备注
     * @param money 金额
     * @param token 秘钥
     */
    @RequestMapping(value = "/stock/insertSelfAccount",method = RequestMethod.POST)
    void insertSelfAccount(@RequestParam("createId") String createId,@RequestParam("type") String type,@RequestParam("time") String time,@RequestParam("remark") String remark,@RequestParam("money") String money,
                           @RequestHeader("Authorization") String token);

    /**
     * 账簿查询所有数据
     * @param createId 用户id
     * @param token 秘钥
     * @return  List<AccountBO>
     */
    @RequestMapping(value = "/stock/listAccount",method = RequestMethod.GET)
    List<AccountBO> listAccount(@RequestParam("createId") int createId,
                                @RequestHeader("Authorization") String token);

    /**
     * 账簿删除数据
     * @param id 账簿编号
     * @param token 秘钥
     */
    @RequestMapping(value = "/stock/deleteAccount",method = RequestMethod.GET)
    void deleteAccount(@RequestParam("id") int id,
                                @RequestHeader("Authorization") String token);

    /**
     * 账簿查询月花费
     * @param createId 用户id
     * @param token 秘钥
     * @return AccountMonthVO
     */
    @RequestMapping(value = "/stock/getMonthModel",method = RequestMethod.GET)
    AccountMonthVO getMonthModel(@RequestParam("createId") int createId,
                       @RequestHeader("Authorization") String token);

    /**
     * 账簿查询周花费
     * @param createId 用户id
     * @param token 秘钥
     * @return List<AccountWeekVO>
     */
    @RequestMapping(value = "/stock/getWeekModel",method = RequestMethod.GET)
    List<AccountWeekVO> getWeekModel(@RequestParam("createId") int createId,
                                 @RequestHeader("Authorization") String token);

    /**
     * 获取本周数据
     * @param createId 用户id
     * @param token 秘钥
     * @return String[]
     */
    @RequestMapping(value = "/stock/getThisWeekData",method = RequestMethod.GET)
    String[] getThisWeekData(@RequestParam("createId") int createId,
                                     @RequestHeader("Authorization") String token);

    /**
     * 获取上周数据
     * @param createId 用户id
     * @param token 秘钥
     * @return String[]
     */
    @RequestMapping(value = "/stock/getLastWeekData",method = RequestMethod.GET)
    String[] getLastWeekData(@RequestParam("createId") int createId,
                             @RequestHeader("Authorization") String token);

    /**
     * 获取本月数据
     * @param createId 用户id
     * @param token 秘钥
     * @return String[]
     */
    @RequestMapping(value = "/stock/getThisMonthData",method = RequestMethod.GET)
    String[] getThisMonthData(@RequestParam("createId") int createId,
                             @RequestHeader("Authorization") String token);

    /**
     * 获取上月数据
     * @param createId 用户id
     * @param token 秘钥
     * @return String[]
     */
    @RequestMapping(value = "/stock/getLastMonthData",method = RequestMethod.GET)
    String[] getLastMonthData(@RequestParam("createId") int createId,
                             @RequestHeader("Authorization") String token);

    /**
     * 获取本月饼图类型
     * @param createId 用户id
     * @param token 秘钥
     * @return String[]
     */
    @RequestMapping(value = "/stock/getMonthTypePie",method = RequestMethod.GET)
    String[] getMonthTypePie(@RequestParam("createId") int createId,
                              @RequestHeader("Authorization") String token);

    /**
     * 获取本月饼图数据
     * @param createId 用户id
     * @param token 秘钥
     * @return String[]
     */
    @RequestMapping(value = "/stock/getMonthValuePie",method = RequestMethod.GET)
    String[] getMonthValuePie(@RequestParam("createId") int createId,
                              @RequestHeader("Authorization") String token);

    /**
     * 获取本年饼图类型
     * @param createId 用户id
     * @param token 秘钥
     * @return String[]
     */
    @RequestMapping(value = "/stock/getYearTypePie",method = RequestMethod.GET)
    String[] getYearTypePie(@RequestParam("createId") int createId,
                              @RequestHeader("Authorization") String token);

    /**
     * 获取本年饼图数据
     * @param createId 用户id
     * @param token 秘钥
     * @return String[]
     */
    @RequestMapping(value = "/stock/getYearValuePie",method = RequestMethod.GET)
    String[] getYearValuePie(@RequestParam("createId") int createId,
                              @RequestHeader("Authorization") String token);

    /**
     * 插入每日清单数据
     * @param information 内容
     * @param endTime 结束时间
     * @param createId 创建id
     * @param token 秘钥
     * @return String
     */
    @RequestMapping(value = "/lifetools/save",method = RequestMethod.POST)
    String save(@RequestParam("information") String information,@RequestParam("endTime") String endTime ,@RequestParam("createId") String createId,
                @RequestHeader("Authorization") String token);

    /**
     * 任务清单
     * @param createId 创建用户id
     * @param token 秘钥
     * @return List<ToDoList>
     */
    @RequestMapping(value = "/lifetools/toDoList",method = RequestMethod.GET)
    List<ToDoList> toDoList(@RequestParam("createId") int createId,
                            @RequestHeader("Authorization") String token);

    /**
     * 查询自己随手记
     * @param createId 用户id
     * @param token 秘钥
     * @return List<Note>
     */
    @RequestMapping(value = "/lifetools/listNoteInfo",method = RequestMethod.GET)
    List<Note> listNoteInfo(@RequestParam("createId") int createId,
                            @RequestHeader("Authorization") String token);

    /**
     * 获取随手记内容
     * @param id 随手记id
     * @param token 秘钥
     * @return Note
     */
    @RequestMapping(value = "/lifetools/getContent",method = RequestMethod.GET)
    Note getContent(@RequestParam("id") int id,
                            @RequestHeader("Authorization") String token);

    /**
     * 删除笔记
     * @param id 随手记id
     * @param createId 用户id
     * @param token 秘钥
     */
    @RequestMapping(value = "/lifetools/deleteNote",method = RequestMethod.GET)
    void deleteNote(@RequestParam("id") int id, @RequestParam("createId") int createId,
                    @RequestHeader("Authorization") String token);
}
