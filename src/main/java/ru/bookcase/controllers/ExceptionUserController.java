package ru.bookcase.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bookcase.services.error.FileExistsException;
import ru.bookcase.services.error.UserExistsException;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ControllerAdvice(basePackages = "ru.bookcase.controllers")
@ResponseBody
public class ExceptionUserController {

    static String MESSAGE = "message";

    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public Map<String, String> getMessageErrorExistsUser() {
        return Map.of(MESSAGE, "Такой пользователь уже существует");
    }

    @ExceptionHandler(FileExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public Map<String, String> getMessageErrorExistsFile(){
        return Map.of(MESSAGE, "Файл с таким именем уже существует");
    }

}
