package org.education.cryptography.controller;

import lombok.RequiredArgsConstructor;
import org.education.cryptography.services.FeignSenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequiredArgsConstructor
public class GenerateDataController {
    private final FeignSenderService feignSenderService;

    @GetMapping("/randomBytes")
    public boolean generate() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {

        return feignSenderService.subscribe();
    }



}
