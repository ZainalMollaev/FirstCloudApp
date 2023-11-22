package org.education.cryptography.controller;

import lombok.RequiredArgsConstructor;
import org.education.cryptography.services.RedisSenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GenerateDataController {

    private final RedisSenderService redisSenderService;

    @GetMapping("/randomBytes")
    public void generate() {
        redisSenderService.subscribe();
    }

}
