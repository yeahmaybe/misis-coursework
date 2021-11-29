package com.misis.coursework.service;


import com.misis.coursework.entity.TransactionEntity;
import com.misis.coursework.entity.UserEntity;
import com.misis.coursework.exceptions.UserNotFoundException;
import com.misis.coursework.model.Transaction;
import com.misis.coursework.repository.TransactionRepo;
import com.misis.coursework.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private UserRepo userRepo;

    public Transaction addTransaction(TransactionEntity transaction,
                                      Long userId)
                                            throws UserNotFoundException  {
        try {
            UserEntity user = userRepo.findById(userId).get();
            transaction.setUser(user);
            return Transaction.toModel(transactionRepo.save(transaction));

        } catch(NoSuchElementException ex) {
            throw new UserNotFoundException(ex.getMessage());
        }

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

        transactionRepo.findAll().forEach(
                e -> counts.add(Transaction.toModel(e)));
        return counts;
    }

    public List<Transaction> findByDescription(String description) {

        ArrayList<TransactionEntity> counts = new ArrayList<>();
        ArrayList<Transaction> res = new ArrayList<>();
        transactionRepo.findAll().forEach(counts::add);
        for(TransactionEntity transaction: counts) {
            if(transaction.getDescription().toUpperCase(Locale.ROOT).contains(description.toUpperCase(Locale.ROOT))) {
                res.add(Transaction.toModel(transaction));
            }
        }
        return res;
    }
    public void importFileCSV(File file) throws IOException {
        //Name | Description | moneyAmount
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            while(line != null) {

                TransactionEntity newTransaction = new TransactionEntity();
                String transaction = line.split("\"")[1];
                List<String> transactionValues = List.of((transaction.split(";")));
                String name = transactionValues.get(0);
                String description = transactionValues.get(1);
                BigDecimal moneyAmuont = BigDecimal.valueOf(Long.parseLong(transactionValues.get(2)));

                newTransaction.setName(name);
                newTransaction.setDescription(description);
                newTransaction.setMoneyAmount(moneyAmuont);

                transactionRepo.save(newTransaction);
                line = reader.readLine();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void uploadCSVFile(MultipartFile file) throws IOException {
        String uuidFile = UUID.randomUUID().toString();
        String fileName = uuidFile + '_' + file.getOriginalFilename();

        File csvFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        file.transferTo(csvFile);
        importFileCSV(csvFile);
    }
}
