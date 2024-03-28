package com.example.nvpproject.Seller.dto;

import com.example.nvpproject.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {
    private Integer idSeller;
    private String lastName;
    private String firstName;
    private String phone;
    private String email;
    private List<Customer> customers;
    private int numberOfCustomers;
    private int totalTransactions;
    private double debtTotalAmountPayed ;
    private double debtTotalAmount ;
}
