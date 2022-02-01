package ru.bookcase.repositories;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bookcase.entity.User;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Repository
public final class UserRepositoryImpl implements UserRepository {

    SessionFactory session;

    @Autowired
    public UserRepositoryImpl(SessionFactory session) {
        this.session = session;
    }


    @Override
    public void insertUser(User newUser) {
        try (var se = session.openSession()) {
            se.beginTransaction();
            se.save(newUser);
            se.getTransaction().commit();
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        try (var se = session.openSession()) {
            return Optional.ofNullable(se.get(User.class, login));
        }
    }
}
