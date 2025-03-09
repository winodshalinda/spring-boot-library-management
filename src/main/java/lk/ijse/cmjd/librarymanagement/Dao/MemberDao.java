package lk.ijse.cmjd.librarymanagement.Dao;

import lk.ijse.cmjd.librarymanagement.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<MemberEntity, String> {
}
