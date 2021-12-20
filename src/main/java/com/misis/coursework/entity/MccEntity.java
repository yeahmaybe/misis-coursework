package com.misis.coursework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class MccEntity {

    @Id
    @Getter @Setter
    private Long code;

    @Getter @Setter
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mcc")
    List<TransactionEntity> transactions;
}
