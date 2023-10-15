package lk.ijse.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lk.ijse.userservice.entity.embedded.NICorPassport;
import lk.ijse.userservice.util.constants.Gender;
import lk.ijse.userservice.util.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class UserDto {
    @Id
    private long userId;
    private String name;
    private Gender gender;
    private Date dob;
    private byte[] proPic;
    private String remarks;
    private NICorPassport nicOrPassport;
    private Date regDate;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private String address;
    private String contact;
    private Role role;
}
