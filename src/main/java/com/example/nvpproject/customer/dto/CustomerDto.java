package com.example.nvpproject.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Integer idCustomer;
    private String  lastName;
    private String firstName;
    private String phone;
    private String email;
    private Double balance;
    private Double monthlyIncome;
    private Double debtAmount;
    private Date dateLastTransaction;
    private Date dateLastPayement;
    private Integer idSeller;
    private  Integer idBusiness;
    private Double debtAmountTotal;

}
