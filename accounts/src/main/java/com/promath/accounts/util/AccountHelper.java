package com.promath.accounts.util;

import com.promath.accounts.constants.AccountConstants;
import com.promath.accounts.entity.Account;
import com.promath.accounts.entity.Customer;

import java.util.Random;

public final class AccountHelper {
    public static final String TEN_DIGITS_PATTERN = "(^$|[0-9]{10})";
    public static final String TWELVE_DIGITS_PATTERN = "(^$|[0-9]{12})";

    private static final int MAX_BOUND = 900_000_000;
    private static final long MIN_BOUND = 1_000_000_000L;

    public static Account createAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(getRandomAccountNumber());
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }

    /**
     *
     * @return a 10-digit account number
     */
    private static long getRandomAccountNumber() {
        return MIN_BOUND + new Random().nextInt(MAX_BOUND);
    }

    public static String constructErrorMessage(String resourceName, String fieldName, String fieldValue) {
        String formatted = String.format("%s not found with the given input data %s : '%s'",
                resourceName, fieldName, fieldValue);
        return formatted;
    }
}
