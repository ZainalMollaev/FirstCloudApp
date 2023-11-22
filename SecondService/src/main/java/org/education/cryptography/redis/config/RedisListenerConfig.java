package org.education.cryptography.redis.config;

import org.education.cryptography.dto.EcdsaDto;
import org.education.cryptography.redis.RedisConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisListenerConfig {

    @Value("${redis.topic.sign}")
    private String signTopic;

    @Value("${redis.topic.gateway}")
    private String gatewayTopic;


    @Bean
    RedisConfiguration redisConfig(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setDatabase(0);
        configuration.setPort(6379);
        configuration.setPassword("mypass");
        configuration.setHostName("localhost");

        return configuration;
    }

    @Bean
    RedisConnectionFactory connectionFactory(RedisConfiguration configuration) {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, LettuceClientConfiguration.builder().build());
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public RedisMessageListenerContainer listenerContainer(MessageListenerAdapter listenerAdapter,
                                                           RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, Arrays.asList(new PatternTopic(signTopic), new PatternTopic(gatewayTopic)));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisConsumer consumer) {
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
