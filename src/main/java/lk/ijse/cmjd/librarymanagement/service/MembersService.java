package lk.ijse.cmjd.librarymanagement.service;

import lk.ijse.cmjd.librarymanagement.dto.MemberDto;

import java.util.List;

public interface MembersService {
    void saveMember(MemberDto dto);
    void updateMember(String id,MemberDto dto);
    void deleteMember(String id);
    MemberDto getMember(String id);
    List<MemberDto> getAllMember();
}
