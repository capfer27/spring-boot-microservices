package com.promath.accounts.service.client;

import com.promath.accounts.dto.LoansDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The value `loans` represent the logical name of the cards service
 * that was registered in the eureka server (dashboard).
 * Note: All methods defined in LoansController
 * should match the ones written in here.
 */
@FeignClient(value = "loans")
public interface LoansFeignClient {

    @GetMapping(path = "/api/loans/fetch", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LoansDTO> fetchLoanDetails(@RequestParam String mobileNumber);
}
