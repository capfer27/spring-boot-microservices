package com.promath.accounts.util;

import com.promath.accounts.constants.AccountConstants;
import com.promath.accounts.entities.Account;
import com.promath.accounts.entities.Customer;

import java.time.LocalDateTime;
import java.util.Random;

public final class AccountHelper {

    private static final int MAX_BOUND = 900_000_000;
    private static final long MIN_BOUND = 1_000_000_000L;

    public static Account createAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());

        newAccount.setAccountNumber(getRandomAccountNumber());
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now()); // TODO: Auto-populate this account using JPA
        newAccount.setCreatedBy(AccountConstants.ANONYMOUS);
        return newAccount;
    }

    /**
     *
     * @return a 10-digit account number
     */
    private static long getRandomAccountNumber() {
        return MIN_BOUND + new Random().nextInt(MAX_BOUND);
    }
}
