package ru.bookcase.entity.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Value
public class BookRequest {

    String title;
    String author;
    MultipartFile file;

    @JsonCreator
    public BookRequest(
            @JsonProperty("title") String title,
            @JsonProperty("author") String author,
            MultipartFile file) {
        this.title = title;
        this.author = author;
        this.file = file;
    }
}
