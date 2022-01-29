package ru.bookcase.repositories;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bookcase.entity.Book;
import ru.bookcase.entity.User;

import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Repository
public class BookRepositoryImpl implements BookRepository {

    SessionFactory session;

    @Autowired
    public BookRepositoryImpl(SessionFactory session) {
        this.session = session;
    }

    @Override
    public Optional<Book> insertBook(Book book) {
        try (var se = session.openSession()) {
            var id = (long) se.save(book);
            return Optional.ofNullable(se.get(Book.class, id));
        }
    }

    @Override
    public void deleteBook(Book book) {
        try (var se = session.openSession()) {
            se.beginTransaction();
            se.remove(book);
            se.getTransaction().commit();
        }
    }

    @Override
    public Optional<Book> updateIsReadBook(Book book) {

        try (var se = session.openSession()) {
            se.beginTransaction();
            se.createQuery("update Book set isRead = :isRead where id = :id")
                    .setParameter("isRead", book.isRead())
                    .setParameter("id", book.getId()).executeUpdate();
            se.getTransaction().commit();
            return Optional.ofNullable(se.get(Book.class, book.getId()));
        }
    }

    @Override
    public Optional<Book> findBookById(long id) {
        try (var se = session.openSession()) {
            return Optional.ofNullable(se.get(Book.class, id));
        }
    }

    @Override
    public List<Book> findAllBooks(String login) {
        try (var se = session.openSession()) {

            var user = se.get(User.class, login);

            return user.getBooks();
        }
    }
}