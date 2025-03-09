package lk.ijse.cmjd.librarymanagement.service;

import lk.ijse.cmjd.librarymanagement.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto saveBook(BookDto bookDto);
    void updateBook(String bookId, BookDto bookDto);
    void deleteBook(String bookId);
    BookDto getSelectedBook(String bookId);
    List<BookDto> getAllBooks();
}
