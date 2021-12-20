package com.misis.coursework.service.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadService {
    @Autowired
    private MccImporter mccImporter;
    @Autowired
    private TypeImporter typeImporter;
    @Autowired
    private TransactionImporter transactionImporter;
    @Autowired
    private UserImporter userImporter;

    private CsvImporter initCsvImporter(CSVType type) {
        switch(type) {
            case TRANSACTION:
                return transactionImporter;
            case MCC:
                return mccImporter;
            case TR_TYPE:
                return typeImporter;
            case USER:
                return userImporter;
            default:
                throw new RuntimeException();
        }
    }

    public void uploadCSVFile(MultipartFile file, CSVType type) throws IOException {
        String uuidFile = UUID.randomUUID().toString();
        String fileName = uuidFile + '_' + file.getOriginalFilename();

        File csvFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        file.transferTo(csvFile);

        CsvImporter importer = initCsvImporter(type);
        importer.importCsv(csvFile);
    }
}
