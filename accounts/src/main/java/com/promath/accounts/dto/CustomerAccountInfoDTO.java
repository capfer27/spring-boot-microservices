package com.promath.accounts.dto;

import com.promath.accounts.entity.Account;
import com.promath.accounts.entity.Customer;

public record CustomerAccountInfoDTO(Customer customer, Account account) {
}
