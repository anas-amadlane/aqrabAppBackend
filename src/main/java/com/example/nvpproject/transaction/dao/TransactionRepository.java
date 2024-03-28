package com.example.nvpproject.transaction.dao;

import com.example.nvpproject.customer.model.Customer;
import com.example.nvpproject.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findTransactionByCustomer(Customer customer);

    List<Transaction> findTransactionByIdCustomer(Integer idCustomer);
}
