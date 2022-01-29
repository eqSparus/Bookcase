package ru.bookcase.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public", catalog = "bookcase_db")
public class User implements Serializable {

    @Id
    @Column(name = "login", unique = true)
    String login;

    @Column(name = "password")
    String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Book> books;
}
