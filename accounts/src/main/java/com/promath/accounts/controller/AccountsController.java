package com.promath.accounts.controller;

import com.promath.accounts.constants.AccountConstants;
import com.promath.accounts.dto.*;
import com.promath.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST API for Account in CapferBank",
        description = "CRUD REST APIs in CapferBank to CREATE, UPDATE, FETCH and DELETE accounts details "
)
public class AccountsController {

    private final IAccountService accountService;
    private final Environment environment;

    private final AccountsContactInfoDTO accountsContactInfoDTO;

    @Value(value = "${build.version}")
    private String version;

    public AccountsController(IAccountService accountService, Environment environment, AccountsContactInfoDTO accountsContactInfoDTO) {
        this.accountService = accountService;
        this.environment = environment;
        this.accountsContactInfoDTO = accountsContactInfoDTO;
    }

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

    @Operation(
            summary = "Get Build Information",
            description = "Get Build Information that is deployed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    @GetMapping(value = "/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(version);
    }

    @Operation(
            summary = "Get Java Version Information",
            description = "Get Java Version Information that is deployed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    @GetMapping(value = "/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("java.version"));
//                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Information",
            description = "The Contact Information that can be used to reach out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    @GetMapping(value = "/contact-info")
    public ResponseEntity<AccountsContactInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDTO);
    }
}
