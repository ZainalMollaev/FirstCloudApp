package org.education.cryptography.controller.feign;

import org.education.cryptography.dto.EcdsaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "feign-convert", url = "localhost:8081")
public interface SignServiceFeignClient {

    @PostMapping(value = "/convert")
    ResponseEntity<EcdsaDto> subscribe(byte[] data);

}
