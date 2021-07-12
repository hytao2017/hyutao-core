package com.hyutao.core.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iflytek.iakpls.security.config.SimpleGrantedAuthorityDeserializer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

/**
 * spring cache redis
 *
 * @author HeLi
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {


  /**
   * Gets redis cache configuration map.
   *
   * @return the redis cache configuration map
   */
  private Map<String, RedisCacheConfiguration> redisCacheConfigurationMap() {
    Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(100);
    //此处添加缓存key 可根据不同业务...
    redisCacheConfigurationMap.put("system", this.redisConfig(3600));
    return redisCacheConfigurationMap;
  }

  /**
   * Gets redis cache configuration with ttl.
   *
   * @param seconds the seconds
   * @return the redis cache configuration with ttl
   */
  private RedisCacheConfiguration redisConfig(Integer seconds) {
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = initJacksonSerializer();
    RedisSerializationContext.SerializationPair<Object> objectSerializationPair = fromSerializer(
        jackson2JsonRedisSerializer);
    return RedisCacheConfiguration.defaultCacheConfig()
        .serializeValuesWith(objectSerializationPair)
        // 缓存ttl
        .entryTtl(Duration.ofSeconds(seconds));
  }


  private Jackson2JsonRedisSerializer<Object> initJacksonSerializer() {
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
        Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
        ObjectMapper.DefaultTyping.NON_FINAL);
    om.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    om.registerModule(new JavaTimeModule());
    om.registerModule(new SimpleModule().addDeserializer(
        SimpleGrantedAuthority.class, new SimpleGrantedAuthorityDeserializer()));
    jackson2JsonRedisSerializer.setObjectMapper(om);
    return jackson2JsonRedisSerializer;
  }

  @Bean
  public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    return RedisCacheManager.RedisCacheManagerBuilder
        .fromCacheWriter(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
        // 默认策略，未配置的 key 会使用这个
        .cacheDefaults(redisConfig(60))
        // 自定义 key 策略
        .withInitialCacheConfigurations(redisCacheConfigurationMap()).build();

  }


  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = initJacksonSerializer();
    template.setValueSerializer(jackson2JsonRedisSerializer);
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.afterPropertiesSet();
    return template;
  }

}
