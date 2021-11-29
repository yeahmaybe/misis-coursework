package com.misis.coursework.controller;

import com.misis.coursework.model.Transaction;
import com.misis.coursework.service.TransactionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class WebController {
    @Autowired
    TransactionService transactionService;

    @RequestMapping("/transactions")
    public String transactionsFiltered(@RequestParam("search") String search, Model model) {
        List<Transaction> transactions = transactionService.findByDescription(search);
        model.addAttribute("transactions", transactions);
        model.addAttribute("search", search);
        return "transactions";
    }
    @RequestMapping("/transactions/")
    public String transactionsFiltered(Model model) {
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("transactions", transactions);
        model.addAttribute("search", "");
        return "transactions";
    }

    @PostMapping("/upload/")
    public void uploadFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        if(!(file.isEmpty())) {
            String[] tmp = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
            if(Objects.equals(tmp[tmp.length - 1], "csv")) {
                transactionService.uploadCSVFile(file);
            }
            model.addAttribute("fileName",  file.getOriginalFilename());
        }
        else {
            model.addAttribute("fileName", "");
        }
    }

    @GetMapping("/upload/") String uploadPage(Model model) {
        model.addAttribute("fileName", "");
        return "upload";
    }
}
