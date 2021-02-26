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

/**
 * 包名称： com.lxz.stock.utils
 * 类名称：StockUtil
 * 类描述：获取股票信息
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/26/13:29
 */

@Component
public class StockUtil {

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


}
