package lk.ijse.travelservice.dto.user;

import lk.ijse.travelservice.dto.user.embedded.NICorPassportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private long userId;
    private String name;
    private String gender;
    private Date dob;
    private String proPic;
    private String remarks;
    private NICorPassportDto niCorPassportDto;
    private Date regDate;
    private Date modifiedDate;
    private String username;
    private String email;
    private String address;
    private String contact;
    private String role;
}
