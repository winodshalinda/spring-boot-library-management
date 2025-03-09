package lk.ijse.cmjd.librarymanagement.service;

import lk.ijse.cmjd.librarymanagement.dto.LendingDto;

import java.util.List;

public interface LendingService {
    void addLending(LendingDto lendingDto);
    void handOverLending(String lendingID);
    void deleteLending(String lendingID);
    LendingDto getSpecificLending(String lendingID);
    List<LendingDto> getAllLendings();
}
