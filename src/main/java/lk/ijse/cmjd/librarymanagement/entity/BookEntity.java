package lk.ijse.cmjd.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="book")
public class BookEntity {
    @Id
    private String bookId;
    private String title;
    private String publisher;
    private String isbn;
    private String author;
    private String edition;
    private Double price;
    private Integer totalQty;
    private Integer availableQty;
    private LocalDate lastUpdatedDate;
    private Time lastUpdatedTime;
    @OneToMany(mappedBy = "book",cascade= CascadeType.ALL,orphanRemoval = true)
    private List<LendingEntity> lending;
}
