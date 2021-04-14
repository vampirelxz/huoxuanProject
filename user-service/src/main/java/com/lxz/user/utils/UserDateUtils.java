package com.lxz.user.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/9/14:20
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.utils
 * 文件描述: @Description: 用户数据统计计算工具类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 包名称： com.lxz.user.utils
 * 类名称：UserDateUtils
 * 类描述：用户数据统计计算工具类
 * 创建人：@author xxxxx
 * 创建时间：2021/4/9/14:20
 */

@Component
public class UserDateUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;
    //每次打开此类只初始化一次countMap
    private static Map countMap = new ConcurrentHashMap<String,Date>();

    public static Map<String,List<Date>> timeMap=new HashMap<String,List<Date>>();

    public static int countVisit = 0;

    public void insertToken(String token) throws ParseException {
        //获得当前时间(毫秒)
//        countVisit++;
        Map map=null;
        try {
            //解析token，获得签发时间
            map = verifyJWT(token);
        } catch (Exception e) {
            throw new RuntimeException("token不存在或已过期");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        Date issuedAt = sdf.parse(map.get("date").toString());
        //email做键，当前时间加5min做值
        Object email=map.get("email");


        countMap.put(email,issuedAt);
    }

    public void updateUseDate(String token) throws ParseException {
        Map map=null;
//        long currentTime = System.currentTimeMillis();
        try {
            //解析token，获得签发时间
            map = verifyJWT(token);
//            System.out.println(map.toString());
        } catch (Exception e) {
            throw new RuntimeException("token不存在或已过期");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        Date issuedAt = sdf.parse(map.get("date").toString());
//        Date issuedAt = (Date) map.get("date");
        //以签发时间为key。当前时间+60s为value存入countMap中
        //email做键，当前时间加5min做值

        Object email=map.get("email");

        if(timeMap.get(email) !=null){
            List<Date> dateList = timeMap.get(map.get("email"));
            dateList.add(issuedAt);
            timeMap.put(map.get("email").toString(),dateList);
        }else{
            List<Date> putList=new ArrayList<>();
            putList.add(issuedAt);
            timeMap.put(email.toString(),putList);
        }
    }

    public Integer getOnlineCount(){
        int onlineCount = 0;
        //获取countMap的迭代器
        Iterator iterator = countMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Date>  entry = (Map.Entry<String, Date>) iterator.next();
            Long value = entry.getValue().getTime();
            if (value > System.currentTimeMillis()-60*5000) {
                //过期时间大于当前时间则没有过期
                onlineCount++;
            }
        }
        return onlineCount;
    }

    private Map verifyJWT(String token){
        Map<String,Object> resultMap = new HashMap<>();
        String email=null;
        Date date=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("XUAN")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            email = jwt.getClaim("email").asString();
            date=jwt.getIssuedAt();
            resultMap.put("email",email);
            resultMap.put("date",date.toString());
        } catch (JWTVerificationException e){
            return null;
        }

        return resultMap;
    }


    static{
        System.out.println("线程启动");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Iterator iterator = countMap.entrySet().iterator();
                    while(iterator.hasNext()){
                        Map.Entry<String,Date>  entry = (Map.Entry<String, Date>) iterator.next();
                        Long value = entry.getValue().getTime();
                        if (value < System.currentTimeMillis()-60*5000) {
                            //已有5分钟没有活跃  判断为下线
                            countMap.remove(entry.getKey());
                            timeMap.remove(entry.getKey());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },5 * 60 * 1000,5 * 60 * 1000);
    }



}
