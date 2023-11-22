package org.education.cryptography.controller.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.education.cryptography.dto.EcdsaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisProducer {

    private final RedisTemplate<String, EcdsaDto> redisTemplate;
    @Value("${redis.topic.sign}")
    private String signTopic;

    public void sendMessage(EcdsaDto ecdsaDto) {
        //todo Обработать com.google.gson.JsonSyntaxException: java.lang.IllegalStateException:
        redisTemplate.convertAndSend(signTopic, ecdsaDto);
    }

}
