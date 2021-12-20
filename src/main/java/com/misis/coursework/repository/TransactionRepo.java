package com.misis.coursework.repository;

import com.misis.coursework.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<TransactionEntity, Long> {
}
