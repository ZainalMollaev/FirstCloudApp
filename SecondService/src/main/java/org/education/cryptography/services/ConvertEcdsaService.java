package org.education.cryptography.services;

import lombok.RequiredArgsConstructor;
import org.education.cryptography.dto.EcdsaDto;
import org.springframework.stereotype.Service;

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
public class ConvertEcdsaService {

    public EcdsaDto sign(byte[] data) throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            SignatureException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");

        ECGenParameterSpec esSpec = new ECGenParameterSpec("secp256r1");

        keyGen.initialize(esSpec);
        KeyPair keyPair = keyGen.generateKeyPair();

        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(keyPair.getPrivate());

        ecdsaSign.update(data);

        byte[] publicKey = Base64.getEncoder().encode(keyPair.getPublic().getEncoded());

        byte[] signature = ecdsaSign.sign();

        return EcdsaDto.builder()
                .message(new String(data))
                .subscribedMessage(new String(signature))
                .publicKey(new String(publicKey))
                .build();
    }
}
