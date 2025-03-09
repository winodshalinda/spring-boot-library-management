package lk.ijse.cmjd.librarymanagement.Dao;

import lk.ijse.cmjd.librarymanagement.entity.LendingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendingDao extends JpaRepository<LendingEntity, String> {
}
