package com.misis.coursework.controller;

import com.misis.coursework.model.Transaction;
import com.misis.coursework.service.TransactionService;
import com.misis.coursework.service.upload.CSVType;
import com.misis.coursework.service.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    UploadService uploadService;

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
    public void uploadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam("type") String type,
                           Model model) throws IOException {

        if(!(file.isEmpty())) {
            CSVType csvType;
            switch(type) {
                case "transaction":
                    csvType = CSVType.TRANSACTION;
                    break;
                case "mcc":
                    csvType = CSVType.MCC;
                    break;
                case "type":
                    csvType = CSVType.TR_TYPE;
                    break;
                case "user":
                    csvType = CSVType.USER;
                    break;
                default:
                    throw new RuntimeException();
            }
            uploadService.uploadCSVFile(file, csvType);
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
