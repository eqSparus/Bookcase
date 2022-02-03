package ru.bookcase.services.error;

import java.io.IOException;

public class FileExistsException extends IOException {

    public FileExistsException() {
        super("Файл с таким названием уже существует");
    }

    public FileExistsException(String message) {
        super(message);
    }

    public FileExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileExistsException(Throwable cause) {
        super(cause);
    }
}
