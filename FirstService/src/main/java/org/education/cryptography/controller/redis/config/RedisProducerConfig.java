package org.education.cryptography.controller.redis.config;

import org.education.cryptography.dto.EcdsaDto;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisProducerConfig {

    @Bean
    RedisTemplate<String, EcdsaDto> redisTemplate(RedisConnectionFactory connectionFactory,
                                                  Jackson2JsonRedisSerializer<EcdsaDto> serializer) {

        RedisTemplate<String, EcdsaDto> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<EcdsaDto> jackson2JsonRedisSerializer() {

        return new Jackson2JsonRedisSerializer<>(EcdsaDto.class);

    }
}
