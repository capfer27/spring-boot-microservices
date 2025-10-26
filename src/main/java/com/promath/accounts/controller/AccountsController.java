package com.promath.accounts.controller;

import com.promath.accounts.constants.AccountConstants;
import com.promath.accounts.dto.CustomerDTO;
import com.promath.accounts.dto.ResponseDTO;
import com.promath.accounts.service.IAccountService;
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
public class AccountsController {

    private final IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        // The ResponseEntity helps send data to the headers
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_CREATED_201, AccountConstants.CREATED_MESSAGE));
    }

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

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdate = accountService.updateAccount(customerDTO);
        if (!isUpdate) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountConstants.SERVER_STATUS_ERROR_500,
                            AccountConstants.SERVER_ERROR_MESSAGE)
                    );
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(AccountConstants.STATUS_OK_200, AccountConstants.MESSAGE_OK));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(
            @RequestParam
            @Pattern(regexp = "^$|[0-9]{10}", message = "The mobileNumber must be 10 digits")
            String mobileNumber
    ) {
        boolean isUpdate = accountService.deleteAccount(mobileNumber);
        if (!isUpdate) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountConstants.SERVER_STATUS_ERROR_500,
                            AccountConstants.SERVER_ERROR_MESSAGE)
                    );
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(AccountConstants.STATUS_OK_200, AccountConstants.MESSAGE_OK));
    }
}
