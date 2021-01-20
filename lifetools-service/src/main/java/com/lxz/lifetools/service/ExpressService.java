package com.lxz.lifetools.service;

import com.lxz.lifetools.entity.Express;
import org.springframework.stereotype.Service;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/18/16:16
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/
@Service
public interface ExpressService {

    Express listExpress(String num, String com);

}
