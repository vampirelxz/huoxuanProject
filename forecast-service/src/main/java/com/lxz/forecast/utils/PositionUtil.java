package com.lxz.forecast.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/13/10:20
 * 项目名称：  huoxuan
 * 文件名称: com.lxz.forecast.utils
 * 文件描述: @Description: ip定位
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.forecast.entity.AmapPosition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 包名称： com.lxz.forecast.utils
 * 类名称：PositionUtil
 * 类描述：ip定位
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/13/10:20
 */

@Component
public class PositionUtil {

    RestTemplate template=null;
    @Resource
    public void setRestTemplate(RestTemplate restTemplate) {
        template = restTemplate;
    }

    @Value("${amap.forecast.key}")
    private String key;


    /**
     * @Title:
     * @Description:
     *
     * @param null
     * @return: String
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/1/13/10:54
     */

    public String getCity(){
        String url="https://restapi.amap.com/v3/ip?"+urlIp(getIp())+"&output=json&key="+key;
        System.out.println(url);
        //String json =restTemplate.getForObject(url,Object.class);
        ResponseEntity<AmapPosition> results = template.exchange(url, HttpMethod.GET, null,  AmapPosition.class);
        AmapPosition json = results.getBody();

        return json.getCity();
    }

    public InetAddress getIp(){
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            System.out.println(ip4.getHostAddress());
            if(!ipIsInner(ip4.toString())){
                return null;
            }
            return ip4;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String urlIp(InetAddress ip){
        if(ip == null){
            return null;
        }else{
            return "ip="+getIp().getHostAddress();
        }
    }

    /**
     * 私有IP：
     * A类  10.0.0.0-10.255.255.255
     * B类  172.16.0.0-172.31.255.255
     * C类  192.168.0.0-192.168.255.255
     *
     * 127这个网段是环回地址
     * localhost
     */
    static List<Pattern> ipFilterRegexList = new ArrayList<>();

    static {
        Set<String> ipFilter = new HashSet<String>();
        ipFilter.add("^10\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])"
                + "\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])" + "\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])$");
        // B类地址范围: 172.16.0.0---172.31.255.255
        ipFilter.add("^172\\.(1[6789]|2[0-9]|3[01])\\" + ".(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])\\"
                + ".(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])$");
        // C类地址范围: 192.168.0.0---192.168.255.255
        ipFilter.add("^192\\.168\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])\\"
                + ".(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])$");
        ipFilter.add("127.0.0.1");
        ipFilter.add("0.0.0.0");
        ipFilter.add("localhost");
        for (String tmp : ipFilter) {
            ipFilterRegexList.add(Pattern.compile(tmp));
        }
    }

    /**
     * 判断IP是否内网IP
     * @Title: ipIsInner
     * @param ip
     * @return: boolean
     */
    public static boolean ipIsInner(String ip) {
        boolean isInnerIp = false;
        for (Pattern tmp : ipFilterRegexList) {
            Matcher matcher = tmp.matcher(ip);
            if (matcher.find()) {
                isInnerIp = true;
                break;
            }
        }
        return isInnerIp;
    }
}
