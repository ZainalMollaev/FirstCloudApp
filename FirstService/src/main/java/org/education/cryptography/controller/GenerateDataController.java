package org.education.cryptography.controller;

import lombok.RequiredArgsConstructor;
import org.education.cryptography.dto.EcdsaDto;
import org.education.cryptography.controller.feign.ConvertDataFeignClient;
import org.education.cryptography.services.CreateData;
import org.education.cryptography.services.EcdsaService;
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
            correct = false;
        } else {
            correct = ecdsaService.isCorrect(ecdsaDto, randomData);
        }

        return correct;
    }
}
