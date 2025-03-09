package lk.ijse.cmjd.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto implements Serializable {
    private String bookId;
    private String title;
    private String publisher;
    private String isbn;
    private String author;
    private String edition;
    private Double price;
    private int totalQty;
    private int availableQty;
    private LocalDate lastUpdatedDate;
    private Time lastUpdatedTime;
}
