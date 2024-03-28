package com.example.nvpproject.users.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data @Entity
@AllArgsConstructor
@NoArgsConstructor @ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String lastName;
    private String firstName;
    private String phone;
   // @Column(name = "email", unique = true)
    private String email;
    private String password;
    private String typeUser;
    private Integer idCustomer;
    private Integer idSeler;

}
