package lk.ijse.guideservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lk.ijse.guideservice.util.constants.Gender;
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
    private long guideId;
    @NotBlank(message = "Name cannot be blank")
    @Pattern(message = "Name should contain only letters",
            regexp = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)$")
    private String name;
    private String gender;
    @NotNull(message = "Date of Birth cannot be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @NotNull(message = "Profile Picture cannot be null or blank")
    private MultipartFile guideImage;
    @Pattern(message = "NIC or Passport is present in invalid format",
            regexp = "^(?:[0-9]{9}[xXvV]|[0-9]{12}|[A-Z][0-9]{8})$")
    private String nicOrPassport;
    @NotNull(message = "Profile Picture cannot be null")
    private MultipartFile frontImg;
    @NotNull(message = "Profile Picture cannot be null")
    private MultipartFile backImg;
    @NotBlank(message = "Address cannot be null or blank")
    private String address;
    @NotBlank(message = "Contact cannot be blank")
    @Pattern(message = "Contact should contain only numbers",
            regexp = "^(?:7|0|(?:\\+94)|(?:94))[0-9]{9,10}$")
    private String contact;
    @NotBlank(message = "Experience cannot be null or blank")
    private String experience;
    @Positive(message = "Day fee should be positive")
    private double dayValue;
}
