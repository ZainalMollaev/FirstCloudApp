package org.education.services.controller;


import lombok.RequiredArgsConstructor;
import org.education.services.services.ConvertEcdsaService;
import org.education.services.dto.EcdsaDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@RequiredArgsConstructor
@RestController
public class ConvertEcdsaController {

    private final ConvertEcdsaService convert;

    @PostMapping(value = "/convert")
    public EcdsaDto subscribe(@RequestBody byte[] data) throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException,
            SignatureException,
            InvalidKeyException {

        EcdsaDto ecdsaDto;

        if (data.length > 0) {
            ecdsaDto =  convert.sign(data);
        } else {
            ecdsaDto = EcdsaDto.builder()
                    .message("Не подписано")
                    .build();
        }

        return ecdsaDto;
    }

}
