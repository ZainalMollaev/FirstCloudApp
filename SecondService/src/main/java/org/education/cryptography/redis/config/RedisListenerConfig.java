package org.education.cryptography.redis.config;

import org.education.cryptography.dto.EcdsaDto;
import org.education.cryptography.redis.EcdsaDtoConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class RedisListenerConfig {

    @Value("${redis.student.topic}")
    private String studentTopic;

    @Bean
    public RedisMessageListenerContainer listenerContainer(MessageListenerAdapter listenerAdapter,
                                                           RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(studentTopic));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(EcdsaDtoConsumer consumer) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(consumer);
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(EcdsaDto.class));
        return messageListenerAdapter;
    }

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
