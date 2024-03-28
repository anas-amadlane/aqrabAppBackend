package com.example.nvpproject.transaction.service;

import com.example.nvpproject.customer.doa.CustomerRepository;
import com.example.nvpproject.customer.model.Customer;
import com.example.nvpproject.transaction.dao.TransactionRepository;
import com.example.nvpproject.transaction.dto.TransactionDTO;
import com.example.nvpproject.transaction.model.Transaction;
import com.example.nvpproject.users.dao.UserRepository;
import com.example.nvpproject.users.entity.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;
    private CustomerRepository customerRepository;
    private UserRepository userRepository;

    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            CustomerRepository customerRepository,
            UserRepository userRepository
            )
    {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public List<TransactionDTO> getListTransactions(Integer idUser) {
        User user = userRepository.getOne(idUser);
        Customer customer = this.customerRepository.getOne(user.getIdCustomer());
        List<Transaction> transactions = this.transactionRepository.findTransactionByCustomer(customer);
        List<TransactionDTO> transactionsDto = transactions.stream().map((Transaction transaction) -> mapTransactionToTransactionDTO(transaction)).collect(Collectors.toList());
        return transactionsDto;
    }

    private TransactionDTO mapTransactionToTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setIdTransaction(transaction.getIdTransaction());
        transactionDTO.setSeller(transaction.getIdSeller().toString());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setDate(transaction.getDate());
        return transactionDTO;
    }




}
