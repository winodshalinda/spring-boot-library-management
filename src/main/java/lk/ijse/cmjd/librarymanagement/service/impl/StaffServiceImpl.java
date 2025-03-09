package lk.ijse.cmjd.librarymanagement.service.impl;

import lk.ijse.cmjd.librarymanagement.Dao.StaffDao;
import lk.ijse.cmjd.librarymanagement.dto.StaffDto;
import lk.ijse.cmjd.librarymanagement.service.StaffService;
import lk.ijse.cmjd.librarymanagement.utill.EntityDtoConversion;
import lk.ijse.cmjd.librarymanagement.exception.StaffNotFoundException;
import lk.ijse.cmjd.librarymanagement.utill.UtilityData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffDao staffDao;
    private final EntityDtoConversion entityDTOConversion;

    @Override
    public void saveStaff(StaffDto staffDto) {
        staffDto.setStaffId(UtilityData.generateStaffId());
        staffDto.setJoinDate(UtilityData.generateTodayDate());
        staffDto.setLastUpdated(UtilityData.generateTodayDate());
        staffDao.save(entityDTOConversion.toStaffEntity(staffDto));
    }

    @Override
    public void updateStaff(String id, StaffDto dto) {
        var foundStaffMember = staffDao.findById(id);
        if(foundStaffMember.isEmpty()){
            throw new StaffNotFoundException("Staff id "+id+" not found");
        }
        foundStaffMember.get().setF_name(dto.getF_name());
        foundStaffMember.get().setL_name(dto.getL_name());
        foundStaffMember.get().setEmail(dto.getEmail());
        foundStaffMember.get().setJoinDate(dto.getJoinDate());
        foundStaffMember.get().setLastUpdated(UtilityData.generateTodayDate());
        foundStaffMember.get().setRole(dto.getRole());
    }

    @Override
    public void deleteStaff(String id) {
        if(staffDao.findById(id).isEmpty()){
            throw new StaffNotFoundException("Staff id "+id+" not found");
        }
        staffDao.deleteById(id);
    }

    @Override
    public StaffDto getStaff(String id) {
        var foundStaffMember = staffDao.findById(id);
        if(foundStaffMember.isEmpty()){
            throw new StaffNotFoundException("Staff id "+id+" not found");
        }
        return entityDTOConversion.toStaffDTO(staffDao.getReferenceById(id));
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return entityDTOConversion.toStaffDTOList(staffDao.findAll());
    }
}
