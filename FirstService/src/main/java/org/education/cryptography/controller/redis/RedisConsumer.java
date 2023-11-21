package org.education.cryptography.controller.redis;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.education.cryptography.dto.EcdsaDto;
import org.education.cryptography.services.EcdsaService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisConsumer implements MessageListener {

    private final EcdsaService ecdsaService;

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String ecdsaJson = new String(message.getBody());
        Gson gson = new Gson();
        EcdsaDto ecdsaDto = gson.fromJson(ecdsaJson, EcdsaDto.class);
        ecdsaService.isCorrect(ecdsaDto);
    }
}
