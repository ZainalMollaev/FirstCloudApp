package org.education.services.controller;

import lombok.RequiredArgsConstructor;
import org.education.services.dto.EcdsaDto;
import org.education.services.controller.feign.ConvertDataFeignClient;
import org.education.services.services.CreateData;
import org.education.services.services.EcdsaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequiredArgsConstructor
public class GenerateDataController {

    private final ConvertDataFeignClient feign;
    private final EcdsaService ecdsaService;
    private final CreateData createData;

    @GetMapping("/randomBytes")
    public boolean generate() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {

        byte[] randomData = createData.randomBytes(200);

        EcdsaDto ecdsaDto = feign.subscribe(randomData).getBody();
        boolean correct;

        if(ecdsaDto.getMessage().startsWith("Не")) {
            correct = ecdsaService.isCorrect(ecdsaDto, randomData);
        } else {
            correct = false;
        }

        return correct;
    }
}
