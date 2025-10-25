package com.promath.accounts.service;

import com.promath.accounts.dto.AccountDTO;
import com.promath.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     *
     * @param customerDTO - {@link CustomerDTO }
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Fetch customer account details
     *
     * @param mobileNumber
     * @return - {@link AccountDTO }
     */
    CustomerDTO getAccountDetails(String mobileNumber);
}
