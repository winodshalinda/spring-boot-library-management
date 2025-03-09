package lk.ijse.cmjd.librarymanagement.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd.librarymanagement.Dao.BookDao;
import lk.ijse.cmjd.librarymanagement.dto.BookDto;
import lk.ijse.cmjd.librarymanagement.entity.BookEntity;
import lk.ijse.cmjd.librarymanagement.exception.BookNotFoundException;
import lk.ijse.cmjd.librarymanagement.service.BookService;
import lk.ijse.cmjd.librarymanagement.utill.EntityDtoConversion;
import lk.ijse.cmjd.librarymanagement.utill.UtilityData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final EntityDtoConversion entityDtoConversion;
    @Override
    public BookDto saveBook(BookDto bookDto) {
        bookDto.setBookId(UtilityData.generateBookId());
        bookDto.setLastUpdatedDate(UtilityData.generateTodayDate());
        bookDto.setLastUpdatedTime(UtilityData.generateCreatedTime());

//        BookEntity bookEntity=entityDtoConversion.toBookEntity(bookDto);
        BookEntity save = bookDao.save(entityDtoConversion.toBookEntity(bookDto));
        return entityDtoConversion.toBookDTO(save);
    }

    @Override
    public void updateBook(String bookId, BookDto bookDto) {
        Optional<BookEntity> foundBook = bookDao.findById(bookId);
        if(!foundBook.isPresent()){
            throw new BookNotFoundException("Book not found");
        }
        foundBook.get().setTitle(bookDto.getTitle());
        foundBook.get().setPublisher(bookDto.getPublisher());
        foundBook.get().setIsbn(bookDto.getIsbn());
        foundBook.get().setAuthor(bookDto.getAuthor());
        foundBook.get().setEdition(bookDto.getEdition());
        foundBook.get().setPrice(bookDto.getPrice());
        foundBook.get().setAvailableQty(bookDto.getAvailableQty());
        foundBook.get().setTotalQty(bookDto.getTotalQty());
        foundBook.get().setLastUpdatedDate(UtilityData.generateTodayDate());
        foundBook.get().setLastUpdatedTime(UtilityData.generateCreatedTime());
    }

    @Override
    public void deleteBook(String bookId) {
        Optional<BookEntity> foundbook=bookDao.findById(bookId);
        if(!foundbook.isPresent()){
            throw new BookNotFoundException("Book not found");
        }
        bookDao.deleteById(bookId);
    }

    @Override
    public BookDto getSelectedBook(String bookId) {
        Optional<BookEntity> foundBook = bookDao.findById(bookId);
        if(!foundBook.isPresent()){
            throw new BookNotFoundException("Book not found");
        }
        return entityDtoConversion.toBookDTO(bookDao.getReferenceById(bookId));
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookEntity> allBooks =bookDao.findAll();
        return entityDtoConversion.toBookDTOList(allBooks);
    }
}
