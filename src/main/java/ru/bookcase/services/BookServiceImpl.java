package ru.bookcase.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bookcase.entity.Book;
import ru.bookcase.entity.dto.BookDto;
import ru.bookcase.entity.request.BookRequest;
import ru.bookcase.repositories.BookRepository;
import ru.bookcase.repositories.UserRepository;
import ru.bookcase.services.error.FileExistsException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public final class BookServiceImpl implements BookService {

    static Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    final BookRepository bookRepository;
    final UserRepository userRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookDto addBook(BookRequest bookRequest, String login) throws IOException {


        var path = Path.of(login + bookRequest.getFile().getOriginalFilename());

        if (Files.exists(path)){
            throw new FileExistsException();
        }

        Files.write(path, bookRequest.getFile().getBytes());

        var book = Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .isRead(false)
                .path(path)
                .user(userRepository.findUserByLogin(login).orElseThrow(IllegalArgumentException::new))
                .build();

        var newBook = bookRepository.insertBook(book).orElseThrow(IllegalArgumentException::new);
        return BookDto.builder()
                .title(newBook.getTitle())
                .author(newBook.getAuthor())
                .id(newBook.getId())
                .isRead(newBook.isRead())
                .build();
    }

    @Override
    public BookDto updateReadingBook(long id, boolean isRead) {

        var book = Book.builder()
                .id(id)
                .isRead(isRead)
                .build();

        var updateBook = bookRepository.updateIsReadBook(book).orElseThrow(IllegalArgumentException::new);

        return BookDto.builder()
                .id(updateBook.getId())
                .isRead(updateBook.isRead())
                .author(updateBook.getAuthor())
                .title(updateBook.getTitle())
                .build();
    }

    @Override
    public void removeBook(long id) throws IOException {
        var book = bookRepository.findBookById(id).orElseThrow(IllegalArgumentException::new);
        Files.delete(book.getPath());
        bookRepository.deleteBook(book);
    }

    @Override
    public List<BookDto> getAllBooks(String login) {

        return bookRepository.findAllBooks(login).stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .isRead(book.isRead())
                        .author(book.getAuthor())
                        .title(book.getTitle())
                        .build())
                .toList();
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findBookById(id).orElseThrow(IllegalArgumentException::new);
    }
}
