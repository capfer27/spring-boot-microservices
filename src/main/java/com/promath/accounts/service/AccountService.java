package com.promath.accounts.service;

import com.promath.accounts.dto.CustomerDTO;
import com.promath.accounts.entities.Account;
import com.promath.accounts.entities.Customer;
import com.promath.accounts.exception.CustomerAlreadyExistsException;
import com.promath.accounts.mapper.CustomerMapper;
import com.promath.accounts.repositories.AccountRepository;
import com.promath.accounts.repositories.CustomerRepository;
import com.promath.accounts.util.AccountHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.promath.accounts.constants.AccountConstants.ANONYMOUS;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     *
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
        Account savedAccount = accountRepository.save(createNewAccount(customer));
    }

    private Account createNewAccount(Customer customer) {
        return AccountHelper.createAccount(customer);
    }
}
