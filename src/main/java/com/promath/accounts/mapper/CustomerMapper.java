package com.promath.accounts.mapper;

import com.promath.accounts.dto.CustomerDTO;
import com.promath.accounts.entities.Customer;

public final class CustomerMapper {

    public CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public Customer mapToCustomerEntity(CustomerDTO customerDTO, Customer customer) {
        customer.setName(customer.getName());
        customer.setEmail(customer.getEmail());
        customer.setMobileNumber(customer.getMobileNumber());
        return customer;
    }
}
