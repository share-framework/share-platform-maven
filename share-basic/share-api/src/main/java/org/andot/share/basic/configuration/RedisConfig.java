package org.andot.share.basic.configuration;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.resource.ClientResources;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePool;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author andot
 */
@Configuration
@EnableCaching
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    /*@Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        RedisNode redisNode_Master = RedisNode.newRedisNode()
                .listeningAt("127.0.0.1", 6377)
                .withName("redis-master").build();
        RedisNode redisNode = RedisNode.newRedisNode()
                .listeningAt("127.0.0.1", 26379)
                .withName("redis-salve-1").build();
        RedisNode redisNode_Salve2 = RedisNode.newRedisNode()
                .listeningAt("127.0.0.1", 6378)
                .withName("redis-salve-2").build();
        redisSentinelConfiguration.sentinel(redisNode_Master).master(() -> "redis-master");

        ClientResources clientResources = ClientResources.builder().computationThreadPoolSize(2).build();
        RedisConfiguration redisConfiguration = new RedisStandaloneConfiguration("127.0.0.1", 6377);
        LettucePoolingClientConfiguration lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
                .clientName("test-1")
                .clientResources(clientResources)
                .build();
        RedisConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(redisConfiguration, lettucePoolingClientConfiguration);
        System.err.println();
        return redisConnectionFactory;
    }*/

    @Bean
    public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }
}
