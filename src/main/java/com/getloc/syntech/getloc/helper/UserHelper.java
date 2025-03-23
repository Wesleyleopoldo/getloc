package com.getloc.syntech.getloc.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.getloc.syntech.getloc.admin.User;
import com.getloc.syntech.getloc.admin.UserRepository;
import com.getloc.syntech.getloc.config.EnviromentsLoad;
import com.getloc.syntech.getloc.exceptions.AccessDeniedException;
import com.getloc.syntech.getloc.exceptions.NotFoundException;
import com.getloc.syntech.getloc.responsesDTO.UserDTO;


@Component
public class UserHelper{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnviromentsLoad enviromentsLoad;

    // public UserHelper() {
    // }

    public UserDTO createUserDTO(User user) {
        return new UserDTO(user.getUserId(), user.getName());
    }

    public boolean isSuperUser(String userId) {
        String superUserId = enviromentsLoad.getUserId();
        return userId.equals(superUserId);
    }

    private boolean isEqualsId(String firstUserId, String secondUserId) {
        return firstUserId.equals(secondUserId);
    }

    public boolean isValidUser(String inUser, String userId) {

        Optional<User> firstUser = userRepository.findById(userId);
        Optional<User> secondUser = userRepository.findById(inUser);
        boolean isSuperUser;
        boolean isEqualId;
        boolean access;

        if(firstUser.isEmpty() || secondUser.isEmpty()) throw new NotFoundException("Usuário não existe!!!");
        
        isSuperUser = isSuperUser(userId);
        isEqualId = isEqualsId(userId, inUser);

        if(!isSuperUser && !isEqualId) throw new AccessDeniedException("Você não tem privilégios para essa ação");

        access = isSuperUser || isEqualId ? true : false;

        return access;
    }
}