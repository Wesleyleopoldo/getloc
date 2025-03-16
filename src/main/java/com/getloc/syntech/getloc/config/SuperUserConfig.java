package com.getloc.syntech.getloc.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.getloc.syntech.getloc.admin.User;
import com.getloc.syntech.getloc.repository.UserRepository;
import com.getloc.syntech.getloc.utils.UtilTools;

@Configuration
public class SuperUserConfig implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnviromentsLoad enviromentsLoad;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        String userIdString = enviromentsLoad.getUserId();
        Optional<User> user = userRepository.findById(userIdString);;

        if(user.isEmpty()) {
            User newUser = User
            .builder()
            .name(enviromentsLoad.getUserName())
            .email(enviromentsLoad.getEmail())
            .password(encoder.encode(enviromentsLoad.getPassword()))
            .build();

            userRepository.save(newUser);

            UtilTools.updateEnviroments(newUser.getUserId());

            System.out.println("Usuário criado com sucesso!!!" + newUser.getUserId());
        } else {
            System.out.println("Usuário já existe!!!" + user.get().getUserId().toString());
        }
    }
}
