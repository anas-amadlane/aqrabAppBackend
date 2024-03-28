package com.example.nvpproject.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Integer idTransaction;
    private String seller;
    private Double amount;
    private Date date;
}
