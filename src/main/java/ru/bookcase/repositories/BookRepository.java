package ru.bookcase.repositories;

import ru.bookcase.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {


    Optional<Book> insertBook(Book book);

    void deleteBook(Book book);

    Optional<Book> updateIsReadBook(Book book);

    Optional<Book> findBookById(long id);

    List<Book> findAllBooks(String login);

}
