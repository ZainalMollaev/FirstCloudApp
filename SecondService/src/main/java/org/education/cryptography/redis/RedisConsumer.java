package org.education.cryptography.redis;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.education.cryptography.dto.EcdsaDto;
import org.education.cryptography.services.ConvertEcdsaService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisConsumer implements MessageListener {

    private final RedisProducer redisProducer;
    private final ConvertEcdsaService convertEcdsaService;

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {

        Gson gson = new Gson();
        EcdsaDto ecdsaDto = gson.fromJson(new String(message.getBody()), EcdsaDto.class);
        ecdsaDto = convertEcdsaService.sign(ecdsaDto.getMessage().getBytes());
        redisProducer.sendMessage(ecdsaDto);

    }

}
