package com.promath.accounts.service;

import com.promath.accounts.dto.AccountDTO;
import com.promath.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     * Create a new account number
     * @param customerDTO - {@link CustomerDTO }
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Fetch customer account details
     * @param mobileNumber - the customer unique mobile number
     * @return - {@link CustomerDTO }
     */
    CustomerDTO getAccountDetails(String mobileNumber);

    /**
     * Update an account by a give data transfer object
     * @param customerDTO - the Data Transfer Object
     * @return - {@link CustomerDTO }
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     * Delete an account based on mobile number
     * @param mobileNumber - the unique object identifier
     * @return - {@link CustomerDTO }
     */
    boolean deleteAccount(String mobileNumber);


}
