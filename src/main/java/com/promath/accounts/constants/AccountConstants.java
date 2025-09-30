package com.promath.accounts.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public final class AccountConstants {

    private AccountConstants() {}

    public static final String SAVINGS = "Savings";
    public static final String ADDRESS = "123 Main Street, New Work";
    public static final String STATUS_CREATED = "201";
    public static final String CREATED_MESSAGE = "Account created successfully";
    public static final String STATUS_OK = "200";
    public static final String MESSAGE_OK = "Account processed successfully";
    private static final String SERVER_ERROR = "500";
    private static final String SERVER_ERROR_MESSAGE = "An error occurred. Please try to contact the Dev team";
}
