package org.education.cryptography.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.education.cryptography.services.RedisSenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GenerateDataController {

    private final RedisSenderService redisSenderService;

    @GetMapping("/randomBytes")
    public void generate() throws JsonProcessingException {
        log.info("Hello, log!");
        redisSenderService.subscribe();
    }

}
