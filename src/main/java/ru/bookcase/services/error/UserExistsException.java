package ru.bookcase.services.error;

public class UserExistsException extends Exception {

    public UserExistsException() {
        super("Такой пользователь уже существует");
    }

    public UserExistsException(String msg) {
        super(msg);
    }

    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistsException(Throwable cause) {
        super(cause);
    }
}
