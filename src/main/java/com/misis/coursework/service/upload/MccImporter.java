package com.misis.coursework.service.upload;

import com.misis.coursework.entity.MccEntity;
import com.misis.coursework.repository.MccRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

@Service
public class MccImporter extends CsvImporterImpl{
    @Autowired
    private MccRepo mccRepo;

    @Override
    public void importCsv(File file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            line = reader.readLine();
            while(line != null) {
                String mcc = line;
                List<String> mccValues = List.of((mcc.split(";")));

                MccEntity newMcc = new MccEntity();
                newMcc.setCode(Long.parseLong(mccValues.get(0)));
                newMcc.setDescription(mccValues.get(1));
                mccRepo.save(newMcc);

                line = reader.readLine();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
