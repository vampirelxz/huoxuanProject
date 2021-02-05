package com.lxz.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lxz.user.entity.User;
import com.lxz.user.service.LoginService;
import com.lxz.user.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

//@CrossOrigin(origins="*",maxAge=3600)
@RestController
public class AuthController {

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
    /**
     * 登录授权，生成JWT
     * @param email
     * @param pwd
     * @return
     */
    @PostMapping("/auth")
    public ResultVO loginAuth(@RequestParam String email,
                                    @RequestParam String pwd){
        Map<String,Object> resultMap = new HashMap<>();
        //账号密码校验
        ResultVO login = loginService.login(email, pwd);
        if(login.isSuccess()){
            //生成JWT
            String token = buildJWT(login.getUser());
            //生成refreshToken
            String refreshToken = UUID.randomUUID().toString().replaceAll("-","");
            //保存refreshToken至redis，使用hash结构保存使用中的token以及用户标识
            String refreshTokenKey = String.format(jwtRefreshTokenKeyFormat, refreshToken);
            stringRedisTemplate.opsForHash().put(refreshTokenKey,
                    "token", token);
            stringRedisTemplate.opsForHash().put(refreshTokenKey,
                    "user", login.getUser().toString());
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

    /**
     * 刷新JWT
     * @param refreshToken
     * @return
     */
    @GetMapping("/token/refresh")
    public Map<String,Object> refreshToken(@RequestParam String refreshToken){
        Map<String,Object> resultMap = new HashMap<>();
        String refreshTokenKey = String.format(jwtRefreshTokenKeyFormat, refreshToken);
        User user = (User)stringRedisTemplate.opsForHash().get(refreshTokenKey,
                "user");
        if(StringUtils.isBlank(user.getName())){
            resultMap.put("code", "10001");
            resultMap.put("msg", "refreshToken过期");
            return resultMap;
        }
        String newToken = buildJWT(user);
        //替换当前token，并将旧token添加到黑名单
        String oldToken = (String)stringRedisTemplate.opsForHash().get(refreshTokenKey,
                "token");
        stringRedisTemplate.opsForHash().put(refreshTokenKey, "token", newToken);
        stringRedisTemplate.opsForValue().set(String.format(jwtBlacklistKeyFormat, oldToken), "",
                tokenExpireTime, TimeUnit.MILLISECONDS);
        resultMap.put("code", "10000");
        resultMap.put("data", newToken);

        return resultMap;
    }

    private String buildJWT(User user){
        //生成jwt
        Date now = new Date();
        Algorithm algo = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
                .withIssuer("XUAN")
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + tokenExpireTime))
                .withClaim("userId", user.getId())
                .withClaim("userName",user.getName())
                .sign(algo);
        System.out.println(token);
        return token;
    }

}