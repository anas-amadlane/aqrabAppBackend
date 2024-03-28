package com.example.nvpproject.transaction.model;

import com.example.nvpproject.Seller.model.Seller;
import com.example.nvpproject.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaction;
    private Integer idSeller;
    private Double amount;
    private Date date;
    private Integer idCustomer;
    @ManyToOne(targetEntity = Customer.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer",insertable = false, updatable = false)
    private Customer customer;
}

