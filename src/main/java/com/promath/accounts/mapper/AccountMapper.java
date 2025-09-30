package com.promath.accounts.mapper;

import com.promath.accounts.dto.AccountDTO;
import com.promath.accounts.entities.Account;

public final class AccountMapper {

    public static AccountDTO mapToAccountDTO(Account account, AccountDTO accountDTO) {
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setBranchAddress(account.getBranchAddress());
        return accountDTO;
    }

    public static Account mapToAccountEntity(AccountDTO accountDTO, Account account) {
        account.setAccountNumber(account.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setBranchAddress(accountDTO.getBranchAddress());
        return account;
    }
}
