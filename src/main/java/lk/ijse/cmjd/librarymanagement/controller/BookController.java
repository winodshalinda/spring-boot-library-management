package lk.ijse.cmjd.librarymanagement.controller;

import lk.ijse.cmjd.librarymanagement.dto.BookDto;
import lk.ijse.cmjd.librarymanagement.exception.BookNotFoundException;
import lk.ijse.cmjd.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {
    private final BookService bookService; //Constructor injection
    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }
    @GetMapping
    public String healthTest() {
        return "hello";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        BookDto dto = bookService.saveBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        try {
            bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (BookNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping
    public ResponseEntity<Void> updateBook(@RequestParam String bookId, @RequestBody BookDto bookDto) {
        try {
            bookService.updateBook(bookId,bookDto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (BookNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> getSelectedBook(@PathVariable String bookId) {
        try {
            return ResponseEntity.ok(bookService.getSelectedBook(bookId));
        }catch (BookNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
