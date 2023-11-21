package org.education.cryptography.services;

import org.education.cryptography.dto.EcdsaDto;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import java.security.spec.EncodedKeySpec;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class EcdsaService {

    public boolean isCorrect(EcdsaDto ecdsaDto) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidKeySpecException {

        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
        EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(ecdsaDto.getPublicKey()));
        KeyFactory kf = KeyFactory.getInstance("EC");
        ecdsaVerify.initVerify(kf.generatePublic(encodedKeySpec));
        ecdsaVerify.update(ecdsaDto.getMessage());

        return ecdsaVerify.verify(ecdsaDto.getMessage());
    }


}
