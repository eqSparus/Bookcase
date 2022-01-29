package ru.bookcase.repositories;

import ru.bookcase.entity.User;

import java.util.Optional;

public interface UserRepository {

    void insertUser(User newUser);

    Optional<User> findUserByLogin(String login);

}
