package com.lxz.gateway.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class AuthFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${auth.skip.urls}")
    private String[] skipAuthUrls;

    @Value("${jwt.blacklist.key.format}")
    private String jwtBlacklistKeyFormat;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int getOrder() {
        return -100;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        //跳过不需要验证的路径
        if(Arrays.asList(skipAuthUrls).contains(url)){
            return chain.filter(exchange);
        }
        //从请求头中取出token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        System.out.println(token);
        //未携带token或token在黑名单内
        if (token == null ||
                token.isEmpty() ||
                    isBlackToken(token)) {
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"code\": \"401\",\"msg\": \"401 Unauthorized.\"}"
                    .getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            System.out.println(401);
            return originalResponse.writeWith(Flux.just(buffer));
        }
        //取出token包含的身份
        int userId = verifyJWT(token);
        System.out.println(userId);
        if(userId==-1){
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.OK);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"code\": \"10002\",\"msg\": \"invalid token.\"}"
                    .getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);

            return originalResponse.writeWith(Flux.just(buffer));
        }
        //将现在的request，添加当前身份
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header("Authorization-UserId", String.valueOf(userId)).build();
        System.out.println(mutableReq.toString());
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    /**
     * JWT验证
     * @param token
     * @return userName
     */
    private int verifyJWT(String token){
        int userId=0;
        System.out.println(token);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("XUAN")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt);
            userId = jwt.getClaim("userId").asInt();

        } catch (JWTVerificationException e){
            LOGGER.error(e.getMessage(), e);
            return -1;
        }
        return userId;
    }

    /**
     * 判断token是否在黑名单内
     * @param token
     * @return
     */
    private boolean isBlackToken(String token){
        assert token != null;
        return stringRedisTemplate.hasKey(String.format(jwtBlacklistKeyFormat, token));
    }
}