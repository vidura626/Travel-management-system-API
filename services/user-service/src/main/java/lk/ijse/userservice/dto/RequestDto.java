package lk.ijse.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestDto {
    @Min(value = 1, message = "UserId cannot be less than 1")
    @Max(value = 9999999999L, message = "UserId cannot be greater than 9999999999")
    private long userId;
    @NotBlank(message = "Name cannot be blank")
    @Pattern(message = "Name should contain only letters",
            regexp = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)$")
    private String name;
    private String gender;
    @NotNull(message = "Date of Birth cannot be null")
//    @Pattern(message = "Date of Birth should be in dd/mm/yyyy format",
//            regexp = "^((0[1-9]|[12]\\d|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d))$")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @NotNull(message = "Profile Picture cannot be null or blank")
    private MultipartFile proPic;
    @NotNull(message = "Remarks cannot be null")
    private String remarks;
    @Pattern(message = "NIC or Passport is present in invalid format",
            regexp = "^(?:[0-9]{9}[xXvV]|[0-9]{12}|[A-Z][0-9]{8})$")
    private String nicOrPassport;
    @NotNull(message = "Profile Picture cannot be null or blank")
    private MultipartFile frontImg;
    @NotNull(message = "Profile Picture cannot be null or blank")
    private MultipartFile backImg;
    private Date regDate;
    @Pattern(regexp = "^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$",
            message = "Username should contain only letters and numbers")
    private String username;
    @Nullable
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Address cannot be null or blank")
    @Size(min = 5, max = 100, message = "Address should be between 5 and 100 characters")
    private String address;
    @Pattern(message = "Contact should contain only numbers",
            regexp = "^(?:7|0|(?:\\+94)|(?:94))[0-9]{9,10}$")
    private String contact;
    @Pattern(message = "Role should be user or admin",
            regexp = "^(user|admin)$")
    private String role;
}
