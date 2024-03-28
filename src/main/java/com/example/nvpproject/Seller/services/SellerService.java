package com.example.nvpproject.Seller.services;


import com.example.nvpproject.Seller.model.Seller;
import com.example.nvpproject.customer.model.Customer;
import com.example.nvpproject.users.dto.UserDto;

import java.util.List;

public interface SellerService {
    public void saveSeller(Seller seller);

    List<Seller> list();

    List<Customer> getListCustomer(UserDto user);
}
