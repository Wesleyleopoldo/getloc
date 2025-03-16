package com.getloc.syntech.getloc.helper;

import org.springframework.stereotype.Component;

import com.getloc.syntech.getloc.admin.User;
import com.getloc.syntech.getloc.responsesDTO.UserDTO;


@Component
public class UserHelper{

    public UserHelper() {
    }

    public UserDTO createUserDTO(User user) {
        return new UserDTO(user.getUserId(), user.getName());
    }
}