package com.lxz.news.config;/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/22/16:02
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.news.config
 * 文件描述: @Description: Redis配置类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * 包名称： com.lxz.news.config
 * 类名称：RedisConfig
 * 类描述：Redis配置类
 * 创建人：@author xxxxx
 * 创建时间：2021/3/22/16:02
 */
@Configuration
public class RedisConfig {

    /**
     * 配置缓存管理器
     * @param factory Redis 线程安全连接工厂
     * @return 缓存管理器
     */
    @Bean
    @Primary
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // 生成两套默认配置，通过 Config 对象即可对缓存进行自定义配置
        RedisCacheConfiguration news = RedisCacheConfiguration.defaultCacheConfig()
                // 设置过期时间 10 分钟
                .entryTtl(Duration.ofMinutes(60))
                // 设置缓存前缀
                .prefixCacheNameWith("cache:")
                // 禁止缓存 null 值
                .disableCachingNullValues()
                // 设置 key 序列化
                .serializeKeysWith(keyPair())
                // 设置 value 序列化
                .serializeValuesWith(valuePair());
//        RedisCacheConfiguration new_junshi = RedisCacheConfiguration.defaultCacheConfig()
//                // 设置过期时间 30 秒
//                .entryTtl(Duration.ofSeconds(30))
//                .prefixKeysWith("cache:new_junshi:")
//                .disableCachingNullValues()
//                .serializeKeysWith(keyPair())
//                .serializeValuesWith(valuePair());
//        RedisCacheConfiguration new_tiyu = RedisCacheConfiguration.defaultCacheConfig()
//                // 设置过期时间 30 秒
//                .entryTtl(Duration.ofSeconds(30))
//                .prefixKeysWith("cache:new_tiyu:")
//                .disableCachingNullValues()
//                .serializeKeysWith(keyPair())
//                .serializeValuesWith(valuePair());
//        RedisCacheConfiguration new_yule = RedisCacheConfiguration.defaultCacheConfig()
//                // 设置过期时间 30 秒
//                .entryTtl(Duration.ofSeconds(30))
//                .prefixKeysWith("cache:new_yule:")
//                .disableCachingNullValues()
//                .serializeKeysWith(keyPair())
//                .serializeValuesWith(valuePair());
//        RedisCacheConfiguration new_shehui = RedisCacheConfiguration.defaultCacheConfig()
//                // 设置过期时间 30 秒
//                .entryTtl(Duration.ofSeconds(30))
//                .prefixKeysWith("cache:new_shehui:")
//                .disableCachingNullValues()
//                .serializeKeysWith(keyPair())
//                .serializeValuesWith(valuePair());
//        RedisCacheConfiguration new_caijing = RedisCacheConfiguration.defaultCacheConfig()
//                // 设置过期时间 30 秒
//                .entryTtl(Duration.ofSeconds(30))
//                .prefixKeysWith("cache:new_caijing:")
//                .disableCachingNullValues()
//                .serializeKeysWith(keyPair())
//                .serializeValuesWith(valuePair());
//        RedisCacheConfiguration new_keji = RedisCacheConfiguration.defaultCacheConfig()
//                // 设置过期时间 30 秒
//                .entryTtl(Duration.ofSeconds(30))
//                .prefixKeysWith("cache:new_keji:")
//                .disableCachingNullValues()
//                .serializeKeysWith(keyPair())
//                .serializeValuesWith(valuePair());
//        RedisCacheConfiguration new_guonei = RedisCacheConfiguration.defaultCacheConfig()
//                // 设置过期时间 30 秒
//                .entryTtl(Duration.ofSeconds(30))
//                .prefixKeysWith("cache:new_guonei:")
//                .disableCachingNullValues()
//                .serializeKeysWith(keyPair())
//                .serializeValuesWith(valuePair());

        // 返回 Redis 缓存管理器
        return RedisCacheManager.builder(factory)
                .withCacheConfiguration("news", news)
//                .withCacheConfiguration("new_junshi", new_junshi)
//                .withCacheConfiguration("new_guonei", new_guonei)
//                .withCacheConfiguration("new_tiyu", new_tiyu)
//                .withCacheConfiguration("new_yule", new_yule)
//                .withCacheConfiguration("new_shehui", new_shehui)
//                .withCacheConfiguration("new_caijing", new_caijing)
//                .withCacheConfiguration("new_keji", new_keji)
                .build();
    }

    /**
     * 配置键序列化
     * @return StringRedisSerializer
     */
    private RedisSerializationContext.SerializationPair<String> keyPair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
    }

    /**
     * 配置值序列化，使用 GenericJackson2JsonRedisSerializer 替换默认序列化
     * @return GenericJackson2JsonRedisSerializer
     */
    private RedisSerializationContext.SerializationPair<Object> valuePair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
    }

}
