package lk.ijse.cmjd.librarymanagement.Dao;

import lk.ijse.cmjd.librarymanagement.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {
}
