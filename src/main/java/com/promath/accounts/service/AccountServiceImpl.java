package com.promath.accounts.service;

import com.promath.accounts.dto.AccountDTO;
import com.promath.accounts.dto.CustomerDTO;
import com.promath.accounts.entities.Account;
import com.promath.accounts.entities.Customer;
import com.promath.accounts.exception.CustomerAlreadyExistsException;
import com.promath.accounts.exception.ResourceNotFoundException;
import com.promath.accounts.mapper.AccountMapper;
import com.promath.accounts.mapper.CustomerMapper;
import com.promath.accounts.repositories.AccountRepository;
import com.promath.accounts.repositories.CustomerRepository;
import com.promath.accounts.util.AccountHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     * Create a new account number
     * @param customerDTO - {@link CustomerDTO }
     */
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

    /**
     * Fetch customer account details
     *
     * @param mobileNumber - the customer mobile number
     * @return Account Details based on mobile number
     */
    @Override
    public CustomerDTO getAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> {
                    String errorMessage = AccountHelper.constructErrorMessage(
                            "Customer",
                            "mobileNumber",
                            mobileNumber
                    );
                    return new ResourceNotFoundException(errorMessage);
                });

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> {
                    String errorMessage = AccountHelper.constructErrorMessage(
                            "Account",
                            "customerId",
                            customer.getCustomerId().toString()
                    );
                    return new ResourceNotFoundException(errorMessage);
                });

        AccountDTO accountDTO = AccountMapper.mapToAccountDTO(account);
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer);
        customerDTO.setAccountDTO(accountDTO);

        return customerDTO;
    }
}
