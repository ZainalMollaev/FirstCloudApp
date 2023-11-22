package org.education.cryptography.controller.redis;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.education.cryptography.dto.EcdsaDto;
import org.education.cryptography.services.EcdsaService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisConsumer implements MessageListener {

    private final EcdsaService ecdsaService;

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {

        if(new String(message.getChannel()).startsWith("gateway")){

            String ecdsaJson = new String(message.getBody(), StandardCharsets.UTF_8);
            Gson gson = new Gson();
            EcdsaDto ecdsaDto = gson.fromJson(ecdsaJson, EcdsaDto.class);
            ecdsaService.isCorrect(ecdsaDto);

        }

    }

}