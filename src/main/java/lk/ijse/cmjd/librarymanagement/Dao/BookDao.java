package lk.ijse.cmjd.librarymanagement.Dao;

import lk.ijse.cmjd.librarymanagement.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<BookEntity, String> {
    @Query("SELECT SUM(b.availableQty) FROM BookEntity b WHERE b.bookId = :bookId")
    int avlQty(@Param("bookId") String bookId);
    @Modifying
    @Query("UPDATE BookEntity b  SET b.availableQty = b.availableQty -1 WHERE b.bookId = :bookId AND b.availableQty > 0")
    void deductBookQtyBasedOnLending(@Param("bookId") String bookId);
    @Modifying
    @Query("UPDATE BookEntity b  SET b.availableQty = b.availableQty +1 WHERE b.bookId = :bookId")
    void addBookAfterHandover(@Param("bookId") String bookId);
}
