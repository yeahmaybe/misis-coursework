package com.misis.coursework.service.upload;

import com.misis.coursework.entity.MccEntity;
import com.misis.coursework.entity.TransactionEntity;
import com.misis.coursework.entity.TypeEntity;
import com.misis.coursework.entity.UserEntity;
import com.misis.coursework.repository.TransactionRepo;
import com.misis.coursework.repository.UserRepo;
import com.misis.coursework.service.MccService;
import com.misis.coursework.service.TypeService;
import com.misis.coursework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

@Service
public class TransactionImporter extends CsvImporterImpl {
    @Autowired
    UserService userService;
    @Autowired
    TypeService typeService;
    @Autowired
    MccService mccService;

    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public void importCsv(File file) {
        // customer_id | tr_datetime | mcc_code | tr_type | amount | term_id

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();
            String headers = line;
            String[] headerList = headers.split(",");
            Map<String, Integer> headerKeys = new HashMap<>();

            int i=0;
            for(String header : headerList) {
                headerKeys.put(header, i);
                i++;
            }

            line = reader.readLine();
            while(line != null) {
                try {
                    TransactionEntity newTransaction = new TransactionEntity();
                    String transaction = line;
                    List<String> transactionValues = List.of((transaction.split(",")));

                    Long customer_id = Long.parseLong(
                            transactionValues.get(
                                    headerKeys.get("customer_id")
                            )
                    );
                    UserEntity user = new UserEntity();
                    user.setId(customer_id);
                    userRepo.save(user);

                    String datetime = transactionValues.get(
                            headerKeys.get("tr_datetime")
                    );

                    Long mcc_code = Long.parseLong(
                            transactionValues.get(
                                    headerKeys.get("mcc_code")
                            )
                    );
                    MccEntity mcc = mccService.getMccEntity(mcc_code);

                    Long type_code = Long.parseLong(
                            transactionValues.get(
                                    headerKeys.get("tr_type")
                            )
                    );
                    TypeEntity type = typeService.getTypeEntity(type_code);

                    BigDecimal moneyAmount = BigDecimal.valueOf(
                            Double.parseDouble(
                                    transactionValues.get(
                                            headerKeys.get("amount")
                                    )
                            )
                    );

                    Long term_id;
                    try {
                        term_id = Long.parseLong(
                                transactionValues.get(
                                        headerKeys.get("term_id")
                                )
                        );
                    } catch (Exception e) {
                        term_id = null;
                    }

                    newTransaction.setDatetime(datetime);
                    newTransaction.setMoneyAmount(moneyAmount);
                    newTransaction.setUser(user);
                    newTransaction.setMcc(mcc);
                    newTransaction.setType(type);
                    if(term_id != null)
                        newTransaction.setTermId(term_id);

                    transactionRepo.save(newTransaction);

                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }

                line = reader.readLine();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}