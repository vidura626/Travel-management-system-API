package lk.ijse.userservice.entity;

import lk.ijse.userservice.entity.embedded.NICorPassport;
import lk.ijse.userservice.util.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class User {
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
    private String password;
    private String email;
    private String address;
    private String contact;
    private String role;
}
