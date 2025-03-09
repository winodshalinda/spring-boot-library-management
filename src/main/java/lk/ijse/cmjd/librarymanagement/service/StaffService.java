package lk.ijse.cmjd.librarymanagement.service;

import lk.ijse.cmjd.librarymanagement.dto.StaffDto;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDto dto);
    void updateStaff(String id,StaffDto dto);
    void deleteStaff(String id);
    StaffDto getStaff(String id);
    List<StaffDto> getAllStaff();
}
