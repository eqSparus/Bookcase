package ru.bookcase.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bookcase.entity.request.UserRequest;
import ru.bookcase.services.UserService;
import ru.bookcase.services.error.UserExistsException;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
public class UserController {

    static String MESSAGE = "message";
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/")
    public String login() {
        return "index";
    }

    @GetMapping(path = "/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public Map<String, String> registrationUser(
            @RequestBody UserRequest userRequest
    ) throws UserExistsException {
        userService.createUser(userRequest);
        return Map.of(MESSAGE, "Пользователь успешно создан");
    }
}
