package com.promath.accounts.service;

import com.promath.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     *
     * @param customerDTO - {@link CustomerDTO }
     */
    void createAccount(CustomerDTO customerDTO);
}
