package com.lxz.lifetools.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/18/15:40
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.utils
 * 文件描述: @Description: 快递工具类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import org.springframework.stereotype.Component;

/**
 * 包名称： com.lxz.lifetools.utils
 * 类名称：ExpressUtil
 * 类描述：快递工具类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/18/15:40
 */

@Component
public class ExpressUtil {
    private String zto="中通快递";
    private String sto="申通快递";
    private String yto="圆通快递";
    private String tiantian="天天快递";
    private String ems="EMS快递";
    private String yunda="韵达快递";
    private String yousu="优速快递";
    private String best="百世快递";
    private String huitong="百世汇通快递";
    private String zhaijisong="宅急送快递";
    private String longbang="龙邦快递";
    private String suning="苏宁快递";
    private String guotong="国通快递";
    private String jingdong="京东快递";

    /**
     * 快递公司转换编码
     * @Title: companyTran
 * @param company
     * @return:  String
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/1/18/15:52
     */
    public String companyTran(String company){
        if(company.equals(zto)){
            return "zto";
        }
        if(company.equals(sto)){
            return "sto";
        }
        if(company.equals(yto)){
            return "yto";
        }
        if(company.equals(tiantian)){
            return "tiantian";
        }
        if(company.equals(ems)){
            return "ems";
        }
        if(company.equals(yunda)){
            return "yunda";
        }
        if(company.equals(yousu)){
            return "yousu";
        }
        if(company.equals(best)){
            return "800best";
        }
        if(company.equals(huitong)){
            return "huitong";
        }
        if(company.equals(zhaijisong)){
            return "zhaijisong";
        }
        if(company.equals(longbang)){
            return "longbang";
        }
        if(company.equals(suning)){
            return "suning";
        }
        if(company.equals(guotong)){
            return "guotong";
        }
        if(company.equals(jingdong)){
            return "jingdong";
        }
        return null;
    }
}
