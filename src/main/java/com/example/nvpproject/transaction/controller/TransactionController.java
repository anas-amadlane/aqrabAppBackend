package com.example.nvpproject.transaction.controller;

import com.example.nvpproject.transaction.dto.TransactionDTO;
import com.example.nvpproject.transaction.service.TransactionService;
import com.example.nvpproject.users.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/aqrab/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionController {

    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public List<TransactionDTO> findTransactionsByCustomer(@RequestParam("idUser") Integer idUser) {
        return transactionService.getListTransactions(idUser);
    }
}
