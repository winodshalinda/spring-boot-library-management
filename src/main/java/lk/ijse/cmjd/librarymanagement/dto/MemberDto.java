package lk.ijse.cmjd.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
    private String memberId;
    private String name;
    private String email;
    private LocalDate memberShipDate;
}
