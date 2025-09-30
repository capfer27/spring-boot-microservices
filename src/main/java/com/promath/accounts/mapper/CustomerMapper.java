package com.promath.accounts.mapper;

import com.promath.accounts.dto.CustomerDTO;
import com.promath.accounts.entities.Customer;

public final class CustomerMapper {

    public CustomerDTO mapToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public Customer mapToCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customer.getName());
        customer.setEmail(customer.getEmail());
        customer.setMobileNumber(customer.getMobileNumber());
        return customer;
    }
}
