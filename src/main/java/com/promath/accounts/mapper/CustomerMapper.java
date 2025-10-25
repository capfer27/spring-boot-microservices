package com.promath.accounts.mapper;

import com.promath.accounts.dto.CustomerDTO;
import com.promath.accounts.entities.Customer;

import java.time.LocalDateTime;

import static com.promath.accounts.constants.AccountConstants.ANONYMOUS;

public final class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        customer.setCreatedAt(LocalDateTime.now()); // TODO: Auto populate this field using JPA
        customer.setCreatedBy(ANONYMOUS);
        return customer;
    }
}
