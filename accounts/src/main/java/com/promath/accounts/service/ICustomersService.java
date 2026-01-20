package com.promath.accounts.service;

import com.promath.accounts.dto.CustomerDetailsDTO;

public interface ICustomersService {
    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobile number
     */
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
