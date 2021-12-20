package com.misis.coursework.service;

import com.misis.coursework.entity.MccEntity;
import com.misis.coursework.entity.TransactionEntity;
import com.misis.coursework.entity.UserEntity;
import com.misis.coursework.exceptions.UserNotFoundException;
import com.misis.coursework.repository.MccRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MccService  {
    @Autowired
    MccRepo mccRepo;

    public MccEntity getMccEntity(Long id) throws UserNotFoundException {
        try {
            return mccRepo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Mcc не найден");
        }
    }
}
