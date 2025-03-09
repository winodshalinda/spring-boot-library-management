package lk.ijse.cmjd.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="lending")
public class LendingEntity {
    @Id
    private String lendingId;
    @ManyToOne
    @JoinColumn(name = "bookId",nullable = false)
    private BookEntity book;
    @ManyToOne
    @JoinColumn(name = "memberId",nullable = false)
    private MemberEntity member;
    private LocalDate lendingDate;
    private LocalDate returnDate;
    private Boolean isActive;
    private Long overDue;
    private Double fineAmount;
}
