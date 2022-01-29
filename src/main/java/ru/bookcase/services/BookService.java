package ru.bookcase.services;

import ru.bookcase.entity.Book;
import ru.bookcase.entity.dto.BookDto;
import ru.bookcase.entity.request.BookRequest;

import java.io.IOException;
import java.util.List;

public interface BookService {

    BookDto addBook(BookRequest bookRequest, String login) throws IOException;

    BookDto updateReadingBook(long id, boolean isRead);

    void removeBook(long id) throws IOException;

    List<BookDto> getAllBooks(String login);

    Book getBookById(long id);
}
