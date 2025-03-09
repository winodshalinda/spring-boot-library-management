package lk.ijse.cmjd.librarymanagement.utill;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UtilityData {
    public static String  generateBookId(){
        return "B-"+UUID.randomUUID();
    }
    public static String  generateLendingId(){
        return "L-"+UUID.randomUUID();
    }
    public static String  generateMemberId(){
        return "M-"+UUID.randomUUID();
    }
    public static String  generateStaffId(){
        return "S-"+UUID.randomUUID();
    }
    public static LocalDate generateTodayDate(){
        return LocalDate.now();
    }
    public static Time generateCreatedTime(){
        return Time.valueOf(LocalDateTime.now().toLocalTime());
    }
    public static LocalDate generateReturnDate(){
        return LocalDate.now().plusDays(7);
    }
}
