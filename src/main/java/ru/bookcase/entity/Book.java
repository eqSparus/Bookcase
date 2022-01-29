package ru.bookcase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.bookcase.entity.convectors.PathFileConvector;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.file.Path;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books", schema = "public", catalog = "bookcase_db")
public class Book implements Serializable {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "title")
    String title;

    @Column(name = "author")
    String author;

    @Column(name = "read")
    boolean isRead;

    @Convert(converter = PathFileConvector.class)
    @Column(name = "path")
    Path path;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
