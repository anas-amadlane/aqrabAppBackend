package com.example.nvpproject.customer.model;

import com.example.nvpproject.Seller.model.Seller;
import com.example.nvpproject.transaction.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor @ToString
public class Customer{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCustomer;
    private String  lastName;
    private String firstName;
    private String phone;
    private String email;
    private Double balance;
    private Double monthlyIncome;
    private Double debtAmount;
    private Double debtAmountTotal;
    private Date  DateLastTransaction;
    private Date  DateLastPayement;
    private Integer idSeller;
    private  Integer idBusiness;

    @ManyToOne(targetEntity = Seller.class, cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name="idSeller",insertable = false, updatable = false)
    private Seller seller;

    @OneToMany(targetEntity = Transaction.class,  cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
