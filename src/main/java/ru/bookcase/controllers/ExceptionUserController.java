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

    @ExceptionHandler({UserExistsException.class, FileExistsException.class})
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public Map<String, String> getMessageErrorExistsUser(Throwable e) {
        return Map.of(MESSAGE, e.getMessage());
    }
}
