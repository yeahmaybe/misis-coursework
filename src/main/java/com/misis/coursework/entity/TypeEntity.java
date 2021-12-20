package com.misis.coursework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
public class TypeEntity {

    @Id
    @Getter
    @Setter
    private Long code;

    @Getter @Setter
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    List<TransactionEntity> transactions;
}
