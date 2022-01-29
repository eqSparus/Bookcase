package ru.bookcase.services;

import ru.bookcase.entity.request.UserRequest;
import ru.bookcase.services.error.UserExistsException;

public interface UserService {

    void createUser(UserRequest userRequest) throws UserExistsException;


}
