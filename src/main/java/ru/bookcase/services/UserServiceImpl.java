package ru.bookcase.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bookcase.entity.Role;
import ru.bookcase.entity.Status;
import ru.bookcase.entity.User;
import ru.bookcase.entity.request.UserRequest;
import ru.bookcase.repositories.UserRepository;
import ru.bookcase.services.error.UserExistsException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public final class UserServiceImpl implements UserService {


    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void createUser(UserRequest userRequest) throws UserExistsException {


        var user = userRepository.findUserByLogin(userRequest.getLogin());

        if (user.isEmpty()) {
            var newUser = User.builder()
                    .login(userRequest.getLogin())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .role(Role.USER)
                    .status(Status.ACTIVE)
                    .build();

            userRepository.insertUser(newUser);
        }else {
            throw new UserExistsException();
        }


    }
}
