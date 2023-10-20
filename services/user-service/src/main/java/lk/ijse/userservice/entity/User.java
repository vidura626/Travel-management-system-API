package lk.ijse.userservice.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lk.ijse.userservice.entity.embedded.NICorPassport;
import lk.ijse.userservice.util.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


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
    private String proPic;
    private String remarks;
    private NICorPassport nicOrPassport;
    private Date regDate;
    private Date modifiedDate;
    private String username;
    private String password;
    private String email;
    private String address;
    private String contact;
    private String role;
}
