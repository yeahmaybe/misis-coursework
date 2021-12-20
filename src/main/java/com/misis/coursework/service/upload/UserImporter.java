package com.misis.coursework.service.upload;

import com.misis.coursework.entity.UserEntity;
import com.misis.coursework.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

@Service
public class UserImporter extends CsvImporterImpl{
    @Autowired
    UserRepo userRepo;

    @Override
    public void importCsv(File file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            line = reader.readLine();
            while(line != null) {
                String type = line;
                List<String> typeValues = List.of((type.split(",")));

                UserEntity newUser = new UserEntity();
                newUser.setId(Long.parseLong(typeValues.get(0)));
                newUser.setGender(Integer.parseInt(typeValues.get(1)));
                userRepo.save(newUser);
                line = reader.readLine();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
