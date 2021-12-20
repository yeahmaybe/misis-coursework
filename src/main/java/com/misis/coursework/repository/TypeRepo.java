package com.misis.coursework.repository;

import com.misis.coursework.entity.MccEntity;
import com.misis.coursework.entity.TypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface TypeRepo extends CrudRepository<TypeEntity, Long> {
}

