package com.misis.coursework.model;

import com.misis.coursework.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class User {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private int gender;

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setGender(entity.getGender());
        return model;
    }
}
