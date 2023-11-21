package org.education.services.controller;

import org.education.services.EcdsaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "feign-convert", url = "localhost:8081")
public interface ConvertDataFeignClient {

    @PostMapping(value = "/convert")
    ResponseEntity<EcdsaDto> subscribe(byte[] data);

}
