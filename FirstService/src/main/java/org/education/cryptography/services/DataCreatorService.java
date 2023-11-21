package org.education.cryptography.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DataCreatorService {

    public byte[] randomBytes(int kb) {
        int dataSize = kb * 1024;

        byte[] randomData = new byte[dataSize];

        Random random = new Random();
        random.nextBytes(randomData);

        return randomData;
    }


}
