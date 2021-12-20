package com.misis.coursework.model;

import com.misis.coursework.entity.MccEntity;
import com.misis.coursework.entity.TransactionEntity;
import com.misis.coursework.repository.MccRepo;
import com.misis.coursework.repository.TypeRepo;
import com.misis.coursework.service.MccService;
import com.misis.coursework.service.TransactionService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

@NoArgsConstructor
public class Transaction {
    @Autowired
    private TransactionService transactionService;

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String date;

    @Getter @Setter
    public String description;

    @Getter @Setter
    public String mcc;

    @Getter @Setter
    private BigDecimal moneyAmount;



}
