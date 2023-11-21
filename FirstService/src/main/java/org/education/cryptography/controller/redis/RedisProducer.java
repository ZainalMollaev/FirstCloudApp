package org.education.cryptography.controller.redis;

import lombok.RequiredArgsConstructor;
import org.education.cryptography.dto.EcdsaDto;
import org.education.cryptography.services.DataCreatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisProducer {

    private final RedisTemplate<String, EcdsaDto> redisTemplate;
    private final DataCreatorService dataCreatorService;


    @Value("${redis.topic.sign}")
    private String messageTopic;

    public void sendMessage(EcdsaDto ecdsaDto) {

        byte[] randomData = dataCreatorService.randomBytes(200);

        redisTemplate.convertAndSend(messageTopic, ecdsaDto);

    }

}
