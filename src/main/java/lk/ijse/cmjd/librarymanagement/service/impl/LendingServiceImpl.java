package lk.ijse.cmjd.librarymanagement.service.impl;

import lk.ijse.cmjd.librarymanagement.Dao.BookDao;
import lk.ijse.cmjd.librarymanagement.Dao.LendingDao;
import lk.ijse.cmjd.librarymanagement.Dao.MemberDao;
import lk.ijse.cmjd.librarymanagement.dto.LendingDto;
import lk.ijse.cmjd.librarymanagement.entity.LendingEntity;
import lk.ijse.cmjd.librarymanagement.entity.MemberEntity;
import lk.ijse.cmjd.librarymanagement.exception.BookNotFoundException;
import lk.ijse.cmjd.librarymanagement.exception.EnoughBookNotFoundException;
import lk.ijse.cmjd.librarymanagement.exception.LendingNotFoundException;
import lk.ijse.cmjd.librarymanagement.exception.MemberNotFoundException;
import lk.ijse.cmjd.librarymanagement.service.LendingService;
import lk.ijse.cmjd.librarymanagement.utill.EntityDtoConversion;
import lk.ijse.cmjd.librarymanagement.utill.LendingMapping;
import lk.ijse.cmjd.librarymanagement.utill.UtilityData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class LendingServiceImpl implements LendingService {
    private final BookDao bookDao;
    private final MemberDao memberDao;
    @Autowired
    private LendingDao lendingDao;
    @Value("${perDayFine}")
    private Double perDayFine;

    private final EntityDtoConversion entityDtoConversion;

    @Override
    public LendingDto addLending(LendingDto lendingDto) {
        String book = lendingDto.getBook();
        String member = lendingDto.getMember();
        //Availability of the book
        var bookEntity = bookDao.findById(book).orElseThrow(() -> new BookNotFoundException("Book not found"));
        // Membership validation
        MemberEntity memberEntity = memberDao.findById(member).orElseThrow(() -> new MemberNotFoundException("Member not found"));
        if(bookDao.avlQty(book) > 0){
            lendingDto.setLendingId(UtilityData.generateLendingId());
            lendingDto.setIsActive(true);
            lendingDto.setLendingDate(UtilityData.generateTodayDate());
            lendingDto.setReturnDate(UtilityData.generateReturnDate());
            lendingDto.setOrverDue(0L);
            lendingDto.setFineAmount(0.00);
            LendingEntity savedEntity = lendingDao.save(LendingMapping.toLendingEntity(lendingDto, bookEntity, memberEntity));
            bookDao.deductBookQtyBasedOnLending(book);
            return entityDtoConversion.toLendingDTO(savedEntity);
        }else {
            throw new EnoughBookNotFoundException("Not AvlQty of Book");
        }
    }

    @Override
    public void handOverLending(String lendingID) {
        var foundLending =
                lendingDao.findById(lendingID).orElseThrow(() -> new LendingNotFoundException("Lending record not found"));
        var returnDate = foundLending.getReturnDate();
        var overdue = calcOverdue(returnDate); //overdue day count
        var fineAmount = calcFineAmount(overdue);
        foundLending.setIsActive(false);
        foundLending.setOverDue(overdue);
        foundLending.setFineAmount(fineAmount);
        bookDao.addBookAfterHandover(foundLending.getBook().getBookId());
    }

    @Override
    public void deleteLending(String lendingID) {
       var foundLending=lendingDao.findById(lendingID).orElseThrow(()-> new LendingNotFoundException("Lending record not found"));
       lendingDao.delete(foundLending);
       //TODO
    }

    @Override
    public LendingDto getSpecificLending(String lendingID) {
       var foundLending=lendingDao.findById(lendingID).orElseThrow(()-> new LendingNotFoundException("Lending record not found"));
       return LendingMapping.toLendingDto(foundLending);
    }

    @Override
    public List<LendingDto> getAllLendings() {
        List<LendingEntity> all = lendingDao.findAll();
        return entityDtoConversion.toAllLendingDto(all);
    }

    private Long calcOverdue(LocalDate returnDate) {
        var today = UtilityData.generateTodayDate();
        if(returnDate.isBefore(today)){
            return ChronoUnit.DAYS.between(returnDate,today);
        }
        return 0L;
    }
    private Double calcFineAmount(Long overdue) {
//        double perDayFine = 5.00;
        return overdue * perDayFine;
    }
}
