package lk.ijse.guideservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lk.ijse.guideservice.entity.embedded.NICorPassport;
import lk.ijse.guideservice.util.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Guide {
    @Id
    @Min(value = 1, message = "UserId cannot be less than 1")
    @Max(value = 9999999999L, message = "UserId cannot be greater than 9999999999")
    private long guideId;
    @NotBlank(message = "Name cannot be blank")
    @Pattern(message = "Name should contain only letters",
            regexp = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)$")
    private String name;
    private Gender gender;
    @NotNull(message = "Date of Birth cannot be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @NotNull(message = "Profile Picture cannot be null or blank")
    private String guideImage;
    private NICorPassport nicOrPassport;
    @NotBlank(message = "Address cannot be null or blank")
    private String address;
    @NotBlank(message = "Contact cannot be blank")
    @Pattern(message = "Contact should contain only numbers",
            regexp = "^(?:7|0|(?:\\+94)|(?:94))[0-9]{9,10}$")
    private String contact;
    @NotBlank(message = "Experience cannot be null or blank")
    private String experience;
}
