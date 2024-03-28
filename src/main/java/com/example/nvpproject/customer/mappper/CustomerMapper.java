package com.example.nvpproject.customer.mappper;

import com.example.nvpproject.customer.dto.CustomerDto;
import com.example.nvpproject.customer.model.Customer;

public class CustomerMapper {
    public CustomerDto customerToCustomerDto(Customer customer) {
        if(customer==null){
            return null;}
        CustomerDto customerDto   =new CustomerDto();
        customerDto.setIdCustomer(customer.getIdCustomer());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        customerDto.setBalance(customer.getBalance());
        return customerDto;
    }
}
