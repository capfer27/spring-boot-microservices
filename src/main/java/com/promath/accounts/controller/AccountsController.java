package com.promath.accounts.controller;

import com.promath.accounts.constants.AccountConstants;
import com.promath.accounts.dto.CustomerDTO;
import com.promath.accounts.dto.ErrorResponseDTO;
import com.promath.accounts.dto.ResponseDTO;
import com.promath.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST API for Account in CapferBank",
        description = "CRUD REST APIs in CapferBank to CREATE, UPDATE, FETCH and DELETE accounts details "
)
public class AccountsController {

    private final IAccountService accountService;

    @Operation(
            summary = "CREATE ACCOUNT REST API",
            description = "REST API to create new Customer and Account in CapferBank"
    )
    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        // The ResponseEntity helps send data to the headers
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_CREATED_201, AccountConstants.CREATED_MESSAGE));
    }

    @Operation(
            summary = "FETCH ACCOUNT REST API",
            description = "REST API to fetch Customer and Account based on a mobile number"
    )
    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    @GetMapping(value = "/details")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(
            @RequestParam
            @Pattern(regexp = "^$|[0-9]{10}", message = "The mobileNumber must be 10 digits")
            String mobileNumber
    ) {
        CustomerDTO customerDetails = accountService.getAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDetails);
    }

    @Operation(
            summary = "UPDATE Account Details REST API",
            description = "REST API to update Customer and Account details based on an account number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdate = accountService.updateAccount(customerDTO);
        if (!isUpdate) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountConstants.UPDATE_STATUS_417,
                            AccountConstants.UPDATE_MESSAGE_417)
                    );
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(AccountConstants.STATUS_OK_200, AccountConstants.MESSAGE_OK));
    }

    @Operation(
            summary = "DELETE Account and Customer Details REST API",
            description = "REST API to delete Customer and Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(
            @RequestParam
            @Pattern(regexp = "^$|[0-9]{10}", message = "The mobileNumber must be 10 digits")
            String mobileNumber
    ) {
        boolean isUpdate = accountService.deleteAccount(mobileNumber);
        if (!isUpdate) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountConstants.DELETE_STATUS_417,
                            AccountConstants.DELETE_MESSAGE_417)
                    );
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(AccountConstants.STATUS_OK_200, AccountConstants.MESSAGE_OK));
    }
}
