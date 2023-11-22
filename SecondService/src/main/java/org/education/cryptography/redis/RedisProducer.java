package org.education.cryptography.redis;

import lombok.RequiredArgsConstructor;
import org.education.cryptography.dto.EcdsaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisProducer {

    @Value("${redis.topic.gateway}")
    private String gatewayTopic;
    private final RedisTemplate<String, EcdsaDto> redisTemplate;

    public void sendMessage(EcdsaDto ecdsaDto) {
        redisTemplate.convertAndSend(gatewayTopic, ecdsaDto);
    }

}
