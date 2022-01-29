package ru.bookcase.entity.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Value
@Builder
@AllArgsConstructor
public class BookDto {

    long id;
    String title;
    String author;
    boolean isRead;


}
