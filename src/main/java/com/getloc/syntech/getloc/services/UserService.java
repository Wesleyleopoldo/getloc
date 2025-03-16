package com.getloc.syntech.getloc.services;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.getloc.syntech.getloc.admin.User;
import com.getloc.syntech.getloc.exceptions.AlreadyExistsException;
import com.getloc.syntech.getloc.exceptions.InvalidArgumentException;
import com.getloc.syntech.getloc.exceptions.NotFoundException;
import com.getloc.syntech.getloc.helper.UserHelper;
import com.getloc.syntech.getloc.repository.UserRepository;
import com.getloc.syntech.getloc.requests.users.SignupBody;
import com.getloc.syntech.getloc.requests.users.UpdateBody;
import com.getloc.syntech.getloc.responsesDTO.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserHelper userHelper;

    private final String EMAIL_REGEX = "^[a-zA-Z0-9_+&+-]+(?:\\.[a-zA-Z0-9_+&+-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public UserDTO postUser(SignupBody data) {
        Optional<User> findUser = this.userRepository.findByEmail(data.userEmail());

        if(findUser.isPresent()) throw new AlreadyExistsException("Email já cadastrado");

        User newUser = User.builder()
        .name(data.userName())
        .email(data.userEmail())
        .password(encoder.encode(data.userPassword()))
        .build();

        userRepository.save(newUser);

        UserDTO userDTO = userHelper.createUserDTO(newUser);

        return userDTO;
    }

    public UserDTO updateUserName(UpdateBody data, String userId) {
        Optional<User> findUser = userRepository.findById(userId);
        if(findUser.isEmpty()) throw new NotFoundException("Esse usuário não existe");

        findUser.get().setName(data.userName());

        User user = findUser.get();

        userRepository.save(user);

        UserDTO userDTO = userHelper.createUserDTO(user);

        return userDTO;
    }

    public UserDTO updateUserEmail(UpdateBody data, String userId) {
        Optional<User> findUser = userRepository.findById(userId);
        if(findUser.isEmpty()) throw new NotFoundException("Esse usuário não existe");

        if(!isValidEmail(data.userEmail())) throw new InvalidArgumentException("Email não válido");

        findUser.get().setEmail(data.userEmail());

        User user = findUser.get();

        userRepository.save(user);

        UserDTO userDTO = userHelper.createUserDTO(user);

        return userDTO;
    }

    public String updateUserPassword(UpdateBody data, String userId) {
        Optional<User> findUser = userRepository.findById(userId);
        if(findUser.isEmpty()) throw new NotFoundException("Esse usuário não existe");

        User user = findUser.get();

        if(data.password().length() < 8) throw new InvalidArgumentException("A senha deve conter no mínimo 8 caracteres");

        if(isEqualsPassword(data.password(), user.getPassword())) throw new AlreadyExistsException("Senha nova não pode ser igual antiga");

        findUser.get().setPassword(encoder.encode(data.password()));

        userRepository.save(user);

        return "Senha atualizada com sucesso!!!";
    }

    public String deleteUserById(String userId) {
        Optional<User> findUser = userRepository.findById(userId);

        if(findUser.isEmpty()) throw new NotFoundException("Esse usuário não existe");

        User user = findUser.get();

        try {
            userRepository.delete(user);
        } catch (Exception ex) {
            throw new InternalError("Algo deu errado na deletação do usuário..." + ex.getMessage());
        }

        return "Sucesso!!!";
    }

    private boolean isEqualsPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
