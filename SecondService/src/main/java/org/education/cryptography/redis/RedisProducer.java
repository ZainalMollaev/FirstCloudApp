package org.education.cryptography.redis;

import lombok.RequiredArgsConstructor;
import org.education.cryptography.dto.EcdsaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisProducer {

    private final RedisTemplate<String, EcdsaDto> redisTemplate;

    @Value("${redis.topic.gateway}")
    private String messageTopic;

    public void sendMessage(EcdsaDto ecdsaDto) {

        redisTemplate.convertAndSend(messageTopic, ecdsaDto);

    }

}
