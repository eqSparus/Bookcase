package ru.bookcase.entity.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Value
public class UserRequest {

    String login;
    String password;

    @JsonCreator
    public UserRequest(
            @JsonProperty("login") String login,
            @JsonProperty("password") String password
    ) {
        this.login = login;
        this.password = password;
    }
}
