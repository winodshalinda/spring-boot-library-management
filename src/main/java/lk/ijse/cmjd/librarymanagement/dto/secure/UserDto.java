package lk.ijse.cmjd.librarymanagement.dto.secure;

import lk.ijse.cmjd.librarymanagement.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
