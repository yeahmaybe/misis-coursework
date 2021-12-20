package com.misis.coursework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String datetime;

    @Getter @Setter
    private BigDecimal moneyAmount;

    @Getter @Setter
    private Long termId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter @Getter
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "mcc_code")
    @Setter @Getter
    private MccEntity mcc;

    @ManyToOne
    @JoinColumn(name = "type")
    @Setter @Getter
    private TypeEntity type;

}
