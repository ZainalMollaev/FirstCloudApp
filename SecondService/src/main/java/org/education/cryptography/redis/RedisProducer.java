package org.education.cryptography.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Value("${redis.topic.gateway}")
    private String gatewayTopic;
    private final RedisTemplate<String, EcdsaDto> redisTemplate;

    public void sendMessage(EcdsaDto ecdsaDto) {
        redisTemplate.convertAndSend(gatewayTopic, ecdsaDto);
    }

}
