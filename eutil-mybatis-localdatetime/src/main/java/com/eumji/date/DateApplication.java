package com.eumji.date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.client.RestTemplate;

/**
 *
 * 其实不算工具类，这是个人测试在java8中mybatis整合localdatetime
 * 详情请看github： https://github.com/mybatis/typehandlers-jsr310
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-5
 * @time: 下午5:15
 */
@SpringBootApplication
public class DateApplication {


//    @Bean
//    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        //设置缓存过期时间
//        cacheManager.setDefaultExpiration(10000);
//        return cacheManager;
//    }
//    @Bean
//    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
//        //StringRedisTemplate template = new RedisTemplate(factory);
//        //setSerializer(template);//设置序列化工具
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        setSerializer(redisTemplate);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//    private void setSerializer(RedisTemplate template){
//        @SuppressWarnings({ "rawtypes", "unchecked" })
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//    }
    public static void main(String[] args) {
        SpringApplication.run(DateApplication.class,args);
    }
}
