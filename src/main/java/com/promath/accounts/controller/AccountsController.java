package com.promath.accounts.controller;

import com.promath.accounts.constants.AccountConstants;
import com.promath.accounts.dto.CustomerDTO;
import com.promath.accounts.dto.ResponseDTO;
import com.promath.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {

    private final IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        // The ResponseEntity helps send data to the headers
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_CREATED_201, AccountConstants.CREATED_MESSAGE));
    }
}
