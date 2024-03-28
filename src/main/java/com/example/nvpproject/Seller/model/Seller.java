package com.example.nvpproject.Seller.model;

import com.example.nvpproject.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.usertype.CompositeUserType;

import javax.persistence.*;
import java.util.List;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor @ToString
public class Seller {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeller;
    private String lastName;
    private String firstName;
    private String phone;
    private String email;

    @OneToMany(targetEntity = Customer.class, cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Customer> customers;
}
