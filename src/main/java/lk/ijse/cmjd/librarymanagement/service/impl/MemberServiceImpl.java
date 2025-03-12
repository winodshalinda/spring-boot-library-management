package lk.ijse.cmjd.librarymanagement.service.impl;

import lk.ijse.cmjd.librarymanagement.Dao.MemberDao;
import lk.ijse.cmjd.librarymanagement.dto.MemberDto;
import lk.ijse.cmjd.librarymanagement.entity.MemberEntity;
import lk.ijse.cmjd.librarymanagement.exception.MemberNotFoundException;
import lk.ijse.cmjd.librarymanagement.exception.StaffNotFoundException;
import lk.ijse.cmjd.librarymanagement.service.MembersService;
import lk.ijse.cmjd.librarymanagement.utill.EntityDtoConversion;
import lk.ijse.cmjd.librarymanagement.utill.UtilityData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MembersService {
    private final MemberDao memberDao;
    private  final EntityDtoConversion entityDTOConversion;

    @Override
    public MemberDto saveMember(MemberDto member) {
        member.setMemberId(UtilityData.generateMemberId());
        member.setMemberShipDate(UtilityData.generateTodayDate());
        MemberEntity savedMemberEntity = memberDao.save(entityDTOConversion.toMemberEntity(member));
        return entityDTOConversion.toMemberDTO(savedMemberEntity);
    }

    @Override
    public void updateMember(String memberId, MemberDto member) {
        var foundMember = memberDao.findById(memberId);
        if(!foundMember.isPresent()){
            throw new MemberNotFoundException("Member id "+memberId+" not found");
        }
        foundMember.get().setName(member.getName());
        foundMember.get().setEmail(member.getEmail());
    }

    @Override
    public void deleteMember(String memberId) {
        if(!memberDao.findById(memberId).isPresent()){
            throw  new MemberNotFoundException("member not found");
        }
        memberDao.deleteById(memberId);
    }

    @Override
    public MemberDto getMember(String id) {
        var foundMember = memberDao.findById(id);
        if(!foundMember.isPresent()){
            throw new StaffNotFoundException("Staff id "+id+" not found");
        }
        return entityDTOConversion.toMemberDTO(memberDao.getReferenceById(id));
    }

    @Override
    public List<MemberDto> getAllMember() {
        return entityDTOConversion.toMemberDTOList(memberDao.findAll());
    }
}
