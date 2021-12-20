package com.misis.coursework.service;


import com.misis.coursework.entity.MccEntity;
import com.misis.coursework.entity.TransactionEntity;
import com.misis.coursework.entity.TypeEntity;
import com.misis.coursework.entity.UserEntity;
import com.misis.coursework.exceptions.UserNotFoundException;
import com.misis.coursework.model.Transaction;
import com.misis.coursework.repository.MccRepo;
import com.misis.coursework.repository.TransactionRepo;
import com.misis.coursework.repository.TypeRepo;
import com.misis.coursework.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction toModel(TransactionEntity entity) {
        Transaction model = new Transaction();

        model.setId(entity.getId());
        model.setDate(entity.getDatetime());
        model.setDescription(entity.getType().getDescription());
        model.setMcc(entity.getMcc().getDescription());
        model.setMoneyAmount(entity.getMoneyAmount());
        return model;
    }

    public Long deleteTransactionById(Long id)
            throws UserNotFoundException {
        try {
            transactionRepo.deleteById(id);
            return id;
        } catch(NoSuchElementException ex) {
            throw new UserNotFoundException(ex.getMessage());
        }
    }
    public List<Transaction> findAll() {
        ArrayList<Transaction> counts = new ArrayList<>();

        for (TransactionEntity tr : transactionRepo.findAll()) {
            counts.add(toModel(tr));
        }
        return counts;
    }

    public List<Transaction> findByDescription(String search) {

        ArrayList<Transaction> counts = new ArrayList<>();
        ArrayList<Transaction> res = new ArrayList<>();
        for (TransactionEntity tr : transactionRepo.findAll()) {
            counts.add(toModel(tr));
        }

        for(Transaction transaction: counts) {
            if(transaction.getDescription().toUpperCase().contains(search.toUpperCase())) {
                res.add(transaction);
            }
        }
        return res;
    }

}