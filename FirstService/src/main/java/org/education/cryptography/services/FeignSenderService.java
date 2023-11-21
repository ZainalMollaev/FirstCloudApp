package org.education.cryptography.services;

import lombok.RequiredArgsConstructor;
import org.education.cryptography.controller.feign.SignServiceFeignClient;
import org.education.cryptography.dto.EcdsaDto;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class FeignSenderService {

    private final SignServiceFeignClient signServiceFeign;
    private final DataCreatorService dataCreatorService;
    private final EcdsaService ecdsaService;

    public boolean subscribe() throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException {

        byte[] randomData = dataCreatorService.randomBytes(200);

        EcdsaDto ecdsaDto = signServiceFeign.subscribe(randomData).getBody();

        return ecdsaService.isCorrect(ecdsaDto, randomData);
    }


}
