package com.misis.coursework.service.upload;

import com.misis.coursework.entity.MccEntity;
import com.misis.coursework.entity.TypeEntity;
import com.misis.coursework.repository.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

@Service
public class TypeImporter extends CsvImporterImpl{
    @Autowired
    TypeRepo typeRepo;

    @Override
    public void importCsv(File file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            line = reader.readLine();
            while(line != null) {
                String type = line;
                List<String> typeValues = List.of((type.split(";")));

                TypeEntity newType = new TypeEntity();
                newType.setCode(Long.parseLong(typeValues.get(0)));
                newType.setDescription(typeValues.get(1));

                typeRepo.save(newType);

                line = reader.readLine();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

