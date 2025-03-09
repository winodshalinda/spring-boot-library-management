package lk.ijse.cmjd.librarymanagement.utill;

import lk.ijse.cmjd.librarymanagement.dto.BookDto;
import lk.ijse.cmjd.librarymanagement.dto.LendingDto;
import lk.ijse.cmjd.librarymanagement.dto.MemberDto;
import lk.ijse.cmjd.librarymanagement.dto.StaffDto;
import lk.ijse.cmjd.librarymanagement.entity.BookEntity;
import lk.ijse.cmjd.librarymanagement.entity.LendingEntity;
import lk.ijse.cmjd.librarymanagement.entity.MemberEntity;
import lk.ijse.cmjd.librarymanagement.entity.StaffEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityDtoConversion {
    private final ModelMapper modelMapper;

    // Book
    public BookDto toBookDTO(BookEntity book) {
        return modelMapper.map(book, BookDto.class);
    }

    public BookEntity toBookEntity(BookDto bookDTO) {
        return modelMapper.map(bookDTO, BookEntity.class);
    }

    public List<BookDto> toBookDTOList(List<BookEntity> books) {
        return modelMapper.map(books, new TypeToken<List<BookDto>>() {
        }.getType());
    }

    // Staff
    public StaffDto toStaffDTO(StaffEntity staff) {
        return modelMapper.map(staff, StaffDto.class);
    }

    public StaffEntity toStaffEntity(StaffDto staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public List<StaffDto> toStaffDTOList(List<StaffEntity> staffs) {
        return modelMapper.map(staffs, new TypeToken<List<StaffDto>>() {
        }.getType());
    }

    // Memeber

    public MemberDto toMemberDTO(MemberEntity member) {
        return modelMapper.map(member, MemberDto.class);
    }

    public MemberEntity toMemberEntity(MemberDto memberDTO) {
        return modelMapper.map(memberDTO, MemberEntity.class);
    }

    public List<MemberDto> toMemberDTOList(List<MemberEntity> members) {
        return modelMapper.map(members, new TypeToken<List<MemberDto>>() {
        }
                .getType());
    }

    public List<LendingDto> toAllLendingDto(List<LendingEntity> entityList) {
        return modelMapper.map(entityList, new TypeToken<List<LendingDto>>(){}.getType());
    }
}
