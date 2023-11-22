package org.education.cryptography.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.education.cryptography.dto.EcdsaDto;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.Signature;

import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConvertEcdsaService {

    public EcdsaDto sign(byte[] data) throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            SignatureException, JsonProcessingException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");

        ECGenParameterSpec esSpec = new ECGenParameterSpec("secp256r1");

        keyGen.initialize(esSpec);
        KeyPair keyPair = keyGen.generateKeyPair();

        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(keyPair.getPrivate());

        ecdsaSign.update(data);

        byte[] publicKey = Base64.getEncoder().encode(keyPair.getPublic().getEncoded());
//        String publicKey = new String(Base64.getEncoder().encode(keyPair.getPublic().toString().getBytes(StandardCharsets.UTF_8)));

        byte[] signature = ecdsaSign.sign();
        ObjectMapper objectMapper = new ObjectMapper();
        return EcdsaDto.builder()
                .message(objectMapper.writeValueAsString(data))
                .subscribedMessage(objectMapper.writeValueAsString(signature))
                .publicKey(new String(publicKey))
                .build();
    }
}
