package org.education.cryptography.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.education.cryptography.controller.redis.RedisProducer;
import org.education.cryptography.dto.EcdsaDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSenderService {

    private final RedisProducer redisProducer;
    private final DataCreatorService dataCreatorService;

    public void subscribe() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] randomData = dataCreatorService.randomBytes(200);
        redisProducer.sendMessage(EcdsaDto.builder()
                        .message(objectMapper.writeValueAsString(randomData))
                .build());

    }


}
