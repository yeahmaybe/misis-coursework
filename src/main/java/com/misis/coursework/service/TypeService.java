package com.misis.coursework.service;

import com.misis.coursework.entity.MccEntity;
import com.misis.coursework.entity.TransactionEntity;
import com.misis.coursework.entity.TypeEntity;
import com.misis.coursework.exceptions.UserNotFoundException;
import com.misis.coursework.repository.MccRepo;
import com.misis.coursework.repository.TypeRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TypeService {
    @Autowired
    TypeRepo typeRepo;

    public TypeEntity getTypeEntity(Long id) throws NotFoundException {
        try {
            return typeRepo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException("Тип не найден");
        }
    }

}
