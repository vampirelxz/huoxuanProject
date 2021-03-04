package com.lxz.stock.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/26/13:29
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.utils
 * 文件描述: @Description: 获取股票信息
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * 包名称： com.lxz.stock.utils
 * 类名称：StockUtil
 * 类描述：获取股票信息
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/26/13:29
 */

@Component
public class StockUtil {
    Pattern pattern = Pattern.compile("[0-9]*");
    Pattern pattern1 = Pattern.compile("[\\u4E00-\\u9FA5|\\\\！|\\\\，|\\\\。|\\\\（|\\\\）|\\\\《|\\\\》|\\\\“|\\\\”|\\\\？|\\\\：|\\\\；|\\\\【|\\\\】]");
    Pattern pattern2 = Pattern.compile("^[a-zA-Z]*");

    public String getStockInfo(URL url) throws IOException {
        InputStream in = url.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } finally {
            if ( in != null) {
                in.close();
            }
        }
        byte b[] = out.toByteArray();
        String s = new String(b, "utf-8");
        return s;
    }

    public String FundTypeTo(String type){
        if(type.equals("fj")){
            return "分级基金";
        }else if(type.equals("currency")){
            return "货币基金";
        }else if(type.equals("stock")){
            return "股票型基金";
        }else if(type.equals("bond")){
            return "债券型基金";
        }else if(type.equals("mix")){
            return "混合型基金";
        }else if(type.equals("qdii")){
            return "QDII基金";
        }else if(type.equals("lof")){
            return "LOF基金";
        }else if(type.equals( "fof")){
            return "FOF基金";
        }else if(type.equals("index")){
            return "指数基金";
        }else if(type.equals("etf")){
            return "ETF基金";
        }
        return type;
    }

    public String FundInOutTo(String inOut){
        if(inOut.equals("in")){
            return "场内";
        }else if(inOut.equals("out")){
            return "场外";
        }
        return inOut;
    }


    public String charJudgment(String code){
        String substring = code.substring(0, 1);
        if(pattern.matcher(substring).matches()){
            return "code";
        }else if(pattern1.matcher(substring).matches()){
            return "chinese";
        }else if(pattern2.matcher(substring).matches()){
            return "english";
        }
        return null;
    }

}
