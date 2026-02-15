package com.promath.accounts.service.client;

import com.promath.accounts.dto.CardsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The value `cards` represent the logical name of the cards service
 * that was registered in the eureka server (dashboard).
 * Note: All methods defined in CardsController
 * should match the ones written in here.
 */
@FeignClient(value = "cards")
public interface CardsFeignClient {

    @GetMapping(path = "/api/fetch", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CardsDTO> fetchCardDetails(@RequestHeader(value = "correlationId") String correlationId, @RequestParam String mobileNumber);
}
