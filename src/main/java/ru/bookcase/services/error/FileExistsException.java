package ru.bookcase.services.error;

import java.nio.file.FileAlreadyExistsException;

public class FileExistsException extends FileAlreadyExistsException {

    public FileExistsException() {
        super("Файл с таким названием уже существует");
    }

    public FileExistsException(String msg) {
        super(msg);
    }
}
