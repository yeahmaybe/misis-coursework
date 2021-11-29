package com.misis.coursework.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<TransactionEntity> transactions;

    public UserEntity() {
    }
}