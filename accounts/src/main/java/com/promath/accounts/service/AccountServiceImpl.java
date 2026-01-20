package com.promath.accounts.service;

import com.promath.accounts.dto.AccountDTO;
import com.promath.accounts.dto.CustomerAccountInfoDTO;
import com.promath.accounts.dto.CustomerDTO;
import com.promath.accounts.entity.Account;
import com.promath.accounts.entity.Customer;
import com.promath.accounts.exception.CustomerAlreadyExistsException;
import com.promath.accounts.exception.ResourceNotFoundException;
import com.promath.accounts.mapper.AccountMapper;
import com.promath.accounts.mapper.CustomerMapper;
import com.promath.accounts.repository.AccountRepository;
import com.promath.accounts.repository.CustomerRepository;
import com.promath.accounts.util.AccountHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered " +
                    "with given mobile number " + customerDTO.getMobileNumber());
        }

        Customer customer = CustomerMapper.mapToCustomerEntity(customerDTO);

        Customer savedCustomer = customerRepository.save(customer);
        // Create new bank account
        accountRepository.save(AccountHelper.createAccount(savedCustomer));
    }

    @Override
    public CustomerDTO getAccountDetails(String mobileNumber) {
        CustomerAccountInfoDTO result = AccountHelper.getCustomerAccountInfo(
                mobileNumber,
                customerRepository,
                accountRepository
        );

        AccountDTO accountDTO = AccountMapper.mapToAccountDTO(result.account());
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(result.customer());
        customerDTO.setAccountDTO(accountDTO);

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();
        if (accountDTO != null) {
            Optional<Account> optionalAccount = accountRepository.findById(accountDTO.getAccountNumber());
            if (optionalAccount.isEmpty()) {
                String message = AccountHelper.constructErrorMessage(
                        "Account", "AccountNumber", accountDTO.getAccountNumber().toString());

                throw new ResourceNotFoundException(message);
            }
            Account account = AccountMapper.mapToAccountEntity(accountDTO, optionalAccount.get());
            Account savedAccount = accountRepository.save(account);

            Long customerId = savedAccount.getCustomerId();
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if (optionalCustomer.isEmpty()) {
                String message = AccountHelper.constructErrorMessage(
                        "Customer",
                        "customerId",
                        customerId.toString()
                );
                throw new ResourceNotFoundException(message);
            }

            Customer customer = CustomerMapper.mapToCustomerEntity(customerDTO, optionalCustomer.get());

            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
            .orElseThrow(() -> {
                String message = AccountHelper.constructErrorMessage(
                    "Customer",
                    "mobileNumber",
                    mobileNumber
                );
                return new ResourceNotFoundException(message);
            });

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
