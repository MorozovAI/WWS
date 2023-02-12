package com.morozov.warrantywebsystem.util;

import com.morozov.warrantywebsystem.model.User;
import com.morozov.warrantywebsystem.to.UserTo;
import lombok.experimental.UtilityClass;

import static com.morozov.warrantywebsystem.config.SecurityConfiguration.PASSWORD_ENCODER;


@UtilityClass
public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {


        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(),
                userTo.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User updatedUser, User user) {
        user.setName(updatedUser.getName());
        user.setPassword(PASSWORD_ENCODER.encode(updatedUser.getPassword()));
        user.setEmail(updatedUser.getEmail().toLowerCase());
        return user;
    }
}