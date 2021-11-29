package com.misis.coursework.controller;

import com.misis.coursework.entity.TransactionEntity;
import com.misis.coursework.exceptions.UserNotFoundException;
import com.misis.coursework.model.Transaction;
import com.misis.coursework.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity addTransaction( @RequestBody TransactionEntity transaction,
                                          @RequestParam Long userId) {
         try {
             return ResponseEntity.ok(transactionService.addTransaction(transaction, userId));
           // "Транзакция добавлена");

         } catch (UserNotFoundException ex) {
             return ResponseEntity.badRequest().body(ex.getMessage());
         } catch (Exception ex) {
             return ResponseEntity.badRequest().body("Произошла ошибка.");
         }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(transactionService.deleteTransactionById(id));
        } catch(UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}
