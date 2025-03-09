package lk.ijse.cmjd.librarymanagement.utill;

import lk.ijse.cmjd.librarymanagement.dto.LendingDto;
import lk.ijse.cmjd.librarymanagement.entity.BookEntity;
import lk.ijse.cmjd.librarymanagement.entity.LendingEntity;
import lk.ijse.cmjd.librarymanagement.entity.MemberEntity;

public class LendingMapping {
    public static LendingDto toLendingDto(LendingEntity lendingEntity){
        LendingDto lendingDto = new LendingDto();
        lendingDto.setLendingId(lendingEntity.getLendingId());
        lendingDto.setLendingDate(lendingEntity.getLendingDate());
        lendingDto.setBook(lendingEntity.getBook().getBookId());
        lendingDto.setMember(lendingEntity.getMember().getMemberId());
        lendingDto.setFineAmount(lendingEntity.getFineAmount());
        lendingDto.setIsActive(lendingEntity.getIsActive());
        lendingDto.setOrverDue(lendingEntity.getOverDue());
        lendingDto.setReturnDate(lendingEntity.getReturnDate());
        return lendingDto;
    }
    public static LendingEntity toLendingEntity(LendingDto lendingDto, BookEntity bookEntity, MemberEntity memberEntity){
        LendingEntity lendingEntity = new LendingEntity();
        lendingEntity.setLendingId(lendingDto.getLendingId());
        lendingEntity.setLendingDate(lendingDto.getLendingDate());
        lendingEntity.setBook(bookEntity);
        lendingEntity.setMember(memberEntity);
        lendingEntity.setFineAmount(lendingDto.getFineAmount());
        lendingEntity.setIsActive(lendingDto.getIsActive());
        lendingEntity.setOverDue(lendingDto.getOrverDue());
        lendingEntity.setReturnDate(lendingDto.getReturnDate());
        return lendingEntity;
    }
}
