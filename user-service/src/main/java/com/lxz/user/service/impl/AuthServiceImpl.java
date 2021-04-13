package com.lxz.user.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/8/13:40
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service.impl
 * 文件描述: @Description: 鉴权实现类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lxz.user.entity.User;
import com.lxz.user.service.AuthService;
import com.lxz.user.service.LoginService;
import com.lxz.user.utils.UserDateUtils;
import com.lxz.user.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 包名称： com.lxz.user.service.impl
 * 类名称：AuthServiceImpl
 * 类描述：鉴权实现类
 * 创建人：@author xxxxx
 * 创建时间：2021/4/8/13:40
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${token.expire.time}")
    private long tokenExpireTime;

    @Value("${refresh.token.expire.time}")
    private long refreshTokenExpireTime;

    @Value("${jwt.refresh.token.key.format}")
    private String jwtRefreshTokenKeyFormat;

    @Value("${jwt.blacklist.key.format}")
    private String jwtBlacklistKeyFormat;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserDateUtils userDateUtils;

    @Override
    public ResultVO loginAuth(String email, String pwd) throws ParseException {
        Map<String,Object> resultMap = new HashMap<>();
        //账号密码校验
        ResultVO login = loginService.login(email, pwd);
        if(login.isSuccess()){
            //生成JWT
            String token = buildJWT(login.getUser());
            userDateUtils.insertToken(token);
            //生成refreshToken
            String refreshToken = UUID.randomUUID().toString().replaceAll("-","");
            //保存refreshToken至redis，使用hash结构保存使用中的token以及用户标识
            String refreshTokenKey = String.format(jwtRefreshTokenKeyFormat, refreshToken);
            stringRedisTemplate.opsForHash().put(refreshTokenKey,
                    "token", token);
            //转为json对象存入
            String jsonString = JSON.toJSONString(login.getUser());
            stringRedisTemplate.opsForHash().put(refreshTokenKey,
                    "user", jsonString);
            //refreshToken设置过期时间
            stringRedisTemplate.expire(refreshTokenKey,
                    refreshTokenExpireTime, TimeUnit.MILLISECONDS);
            //返回结果
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("token", token);
            dataMap.put("refreshToken", refreshToken);
            resultMap.put("code", "10000");
            resultMap.put("data", dataMap);
            login.setToken(token);
            login.setRefreshToken(refreshToken);
            System.out.println(login.toString());
            return login;
        }
        resultMap.put("isSuccess", false);
        login.setSuccess(false);
        System.out.println(login.toString());
        return login;
    }

    @Override
    public Map<String, Object> refreshToken(String refreshToken) throws ParseException {
        Map<String,Object> resultMap = new HashMap<>();
        String refreshTokenKey = String.format(jwtRefreshTokenKeyFormat, refreshToken);
        User user=JSON.parseObject(stringRedisTemplate.opsForHash().get(refreshTokenKey,
                "user").toString(),User.class);
        if(StringUtils.isBlank(user.getName())){
            resultMap.put("code", "10001");
            resultMap.put("msg", "refreshToken过期");
            return resultMap;
        }
        String newToken = buildJWT(user);
        userDateUtils.updateUseDate(newToken);
        //替换当前token，并将旧token添加到黑名单  不是refreshToken
        String oldToken = (String)stringRedisTemplate.opsForHash().get(refreshTokenKey,
                "token");
        stringRedisTemplate.opsForHash().put(refreshTokenKey, "token", newToken);
        stringRedisTemplate.opsForValue().set(String.format(jwtBlacklistKeyFormat, oldToken), "",
                tokenExpireTime, TimeUnit.MILLISECONDS);
        resultMap.put("code", "10000");
        resultMap.put("data", newToken);

        return resultMap;
    }

    @Override
    public String buildJWT(User user) {
        //生成jwt
        Date now = new Date();
        Algorithm algo = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
                .withIssuer("XUAN")
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + tokenExpireTime))
                .withClaim("userId", user.getId())
                .withClaim("userName",user.getName())
                .withClaim("email",user.getEmail())
                .sign(algo);
        return token;
    }

    @Override
    public String buildForgotJWT(String email) {
        //生成jwt
        Date now = new Date();
        Algorithm algo = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
                .withIssuer("XUAN")
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + tokenExpireTime))
                .withClaim("email", email)
                .withClaim("userId", 0000)
                .sign(algo);
        return token;
    }

    @Override
    public ResultVO updatePwdAuth(String email) {
        Map<String,Object> resultMap = new HashMap<>();
        //账号密码校验
        ResultVO resultVO = new ResultVO();
        //生成JWT
        String token = buildForgotJWT(email);
        //返回结果
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("token", token);
        resultMap.put("code", "10000");
        resultMap.put("data", dataMap);
        resultVO.setToken(token);
        System.out.println(resultVO.toString());
        return resultVO;
    }
}
