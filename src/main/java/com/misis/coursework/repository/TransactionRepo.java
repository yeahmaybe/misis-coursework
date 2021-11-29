package com.misis.coursework.repository;

import com.misis.coursework.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<TransactionEntity, Long> {
}
