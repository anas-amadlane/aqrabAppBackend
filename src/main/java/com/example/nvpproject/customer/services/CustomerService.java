package com.example.nvpproject.customer.services;

import com.example.nvpproject.customer.dto.CustomerDto;
import com.example.nvpproject.customer.model.Customer;

import java.util.List;

public interface CustomerService {
    public void saveCustomer();
   public List<Customer> list();
    CustomerDto updateCustomer(Integer amountTransaction, Integer idCustomer);
    List<CustomerDto> getListCustomers(Integer idUser);

    CustomerDto getCustomer(Integer idCustomer);

    void addCustomer(CustomerDto customerDto);

    void payDebt(CustomerDto customerDto);

}
