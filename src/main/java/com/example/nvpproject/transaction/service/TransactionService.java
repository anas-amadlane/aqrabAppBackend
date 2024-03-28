package com.example.nvpproject.transaction.service;

import com.example.nvpproject.transaction.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getListTransactions(Integer idUser);
}
