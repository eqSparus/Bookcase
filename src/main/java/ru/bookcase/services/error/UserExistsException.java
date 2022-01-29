package ru.bookcase.services.error;

public class UserExistsException extends Exception {

    public UserExistsException() {
        super("User already exists");
    }

    public UserExistsException(String msg) {
        super(msg);
    }
}
