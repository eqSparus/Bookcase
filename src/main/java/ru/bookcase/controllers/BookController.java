package ru.bookcase.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bookcase.entity.dto.BookDto;
import ru.bookcase.entity.request.BookRequest;
import ru.bookcase.services.BookService;

import java.io.IOException;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@RequestMapping(path = "/book")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getPageBooks() {
        return "books";
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<BookDto> getAllBooks(
            @AuthenticationPrincipal UserDetails user
    ) {
        return bookService.getAllBooks(user.getUsername());
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, params = {"title", "author"})
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public BookDto createBook(
            @AuthenticationPrincipal UserDetails user,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "author") String author,
            @RequestBody MultipartFile file
    ) throws IOException {
        return bookService.addBook(new BookRequest(title, author, file), user.getUsername());
    }

    @PutMapping(params = {"id", "isRead"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BookDto updateStatusReading(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "isRead") boolean isRead
    ) {
        return bookService.updateReadingBook(id, isRead);
    }

    @DeleteMapping(params = "id")
    @ResponseBody
    public ResponseEntity<String> deleteBook(
            @RequestParam(name = "id") long id
    ) throws IOException {
        bookService.removeBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/download/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> downloadFile(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable(name = "id") long id
    ) throws IOException {

        var book = bookService.getBookById(id);
        var resource = new UrlResource(book.getPath().toUri());

        var fileName = resource.getFilename().replace(user.getUsername(), "");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        fileName + "\"")
                .body(resource);

    }
}
