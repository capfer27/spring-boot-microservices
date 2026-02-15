package com.promath.accounts.controller;

import com.promath.accounts.dto.CustomerDetailsDTO;
import com.promath.accounts.dto.ErrorResponseDTO;
import com.promath.accounts.service.ICustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST API for Customers in CapferBank",
        description = "REST APIs in CapferBank to FETCH customers details "
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Slf4j
public class CustomerController {

    private final ICustomersService customersService;

    public CustomerController(ICustomersService customersService) {
        this.customersService = customersService;
    }

    @Operation(
            summary = "FETCH Customer Details REST API",
            description = "REST API to fetch Customer Details based on a mobile number"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @GetMapping(path = "/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(
            @RequestParam
            @Pattern(regexp = "^$|[0-9]{10}", message = "The mobileNumber must be 10 digits")
            String mobileNumber,
            @RequestHeader(value = "capferbank-correlation-id") String correlationId
    ) {
        log.debug("CapferBank-correlation-id found {}", correlationId);
        CustomerDetailsDTO customerDetailsDTO = this.customersService.fetchCustomerDetails(mobileNumber, correlationId);
//        return new ResponseEntity<>(customerDetailsDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDTO);
    }
}
