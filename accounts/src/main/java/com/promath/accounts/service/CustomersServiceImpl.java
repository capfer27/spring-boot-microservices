package com.promath.accounts.service;

import com.promath.accounts.dto.*;
import com.promath.accounts.entity.Account;
import com.promath.accounts.entity.Customer;
import com.promath.accounts.mapper.AccountMapper;
import com.promath.accounts.mapper.CustomerMapper;
import com.promath.accounts.repository.AccountRepository;
import com.promath.accounts.repository.CustomerRepository;
import com.promath.accounts.service.client.CardsFeignClient;
import com.promath.accounts.service.client.LoansFeignClient;
import com.promath.accounts.util.AccountHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    public CustomersServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, CardsFeignClient cardsFeignClient, LoansFeignClient loansFeignClient) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
    }


    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobile number
     */
    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber) {
        CustomerAccountInfoDTO customerAccountInfo = AccountHelper.getCustomerAccountInfo(
                mobileNumber,
                customerRepository,
                accountRepository
        );



        Customer customer = customerAccountInfo.customer();
        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDTO(customer);

        Account account = customerAccountInfo.account();
        AccountDTO accountDTO = AccountMapper.mapToAccountDTO(account);

        customerDetailsDTO.setAccountDTO(accountDTO);

        // Populate Loans and Cards Information.
        // Therefore, we need to make a request to these services.

        // Fetch Loans Info
        ResponseEntity<LoansDTO> loansDTOResponseEntity = this.loansFeignClient.fetchLoanDetails(mobileNumber);
        LoansDTO loansDTO = loansDTOResponseEntity.getBody();
        customerDetailsDTO.setLoansDTO(loansDTO);

        // Fetch Cards DTO
        ResponseEntity<CardsDTO> cardsDTOResponseEntity = this.cardsFeignClient.fetchCardDetails(mobileNumber);
        CardsDTO cardsDTO = cardsDTOResponseEntity.getBody();
        customerDetailsDTO.setCardsDTO(cardsDTO);

        return customerDetailsDTO;
    }
}
