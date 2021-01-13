package com.xiaomai.cloud.nacoswebflux.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 *
 * 默认使用的JdkSerializationRedisSerializer 这样的会导致我们通过redis desktop manager显示的我们key跟value的时候显示不是正常字符
 *
 * @author developer
 * @date 2021/1/12
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 配置自定义redisTemplate
     * @return
     */
    @Bean
    @Primary
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        // 设置值（value）的序列化采用Jackson2JsonRedisSerializer。
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {

        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromCacheWriter(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                // 默认策略，未配置的 key 会使用这个
                .cacheDefaults(redisConfig(60))
                // 自定义 key 策略
                .withInitialCacheConfigurations(redisCacheConfigurationMap())
                .build();
    }

    private Map<String, RedisCacheConfiguration> redisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(16);

//        Class<CacheName> cacheNameEnumClass = CacheNameEnum.class;
//        final Enum<?>[] enums = cacheNameEnumClass.getEnumConstants();
//        for (Enum<?> e : enums) {
//            CacheNameEnum cacheEnumer = (CacheNameEnum) e;
//            redisCacheConfigurationMap.put(cacheEnumer.cacheName(), this.redisConfig(cacheEnumer.ttlSecond()));
//        }

        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration redisConfig(Integer seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = initJacksonSerializer();
        RedisSerializationContext.SerializationPair<Object> objectSerializationPair = RedisSerializationContext
                .SerializationPair
                .fromSerializer(jackson2JsonRedisSerializer);

        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(objectSerializationPair)
                // 缓存ttl
                .entryTtl(Duration.ofSeconds(seconds));
    }

    private Jackson2JsonRedisSerializer<Object> initJacksonSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //Jackson2反序列化数据处理LocalDateTime类型时出错
        om.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        //允许使用未带引号的字段名
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //允许使用单引号
        om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

}