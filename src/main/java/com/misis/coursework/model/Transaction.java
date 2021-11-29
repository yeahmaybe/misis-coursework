package com.misis.coursework.model;

import com.misis.coursework.entity.TransactionEntity;
import com.misis.coursework.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
public class Transaction {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    public String name;

    @Getter @Setter
    public String description;

    @Getter @Setter
    private BigDecimal moneyAmount;

    public static Transaction toModel(TransactionEntity entity) {
        Transaction model = new Transaction();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setMoneyAmount(entity.getMoneyAmount());

        return model;
    }
}
