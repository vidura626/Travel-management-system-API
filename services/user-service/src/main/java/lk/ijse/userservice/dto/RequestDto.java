package lk.ijse.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Min(value = 1, message = "UserID cannot be less than 1")
    private long userId;
    @NotBlank(message = "Name cannot be blank")
    @Pattern(message = "Name should contain only letters",
            regexp = "^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$")
    private String name;
    @Pattern(message = "Gender should be male or female",
            regexp = "^(male|female)$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String gender;
    @NotNull(message = "Date of Birth cannot be null")
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
    @Pattern(message = "Username should contain only letters and numbers",
            regexp = "^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$")
    private String username;
    @Pattern(message = "Password should contain at least 8 characters that contains minimum of one uppercase, one lowercase, one number and one special character",
            regexp = "^(?=.*[A-Za-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
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
            regexp = "^(user|admin)$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String role;
}
