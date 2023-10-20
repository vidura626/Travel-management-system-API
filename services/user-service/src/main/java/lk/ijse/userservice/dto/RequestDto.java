package lk.ijse.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    private long userId;
    private String name;
    private String gender;
    private Date dob;
    private MultipartFile proPic;
    private String remarks;
    private String nicOrPassport;
    private MultipartFile frontImg;
    private MultipartFile backImg;
    private Date regDate;
    @NotBlank(message = "Username cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$",
            message = "Username should contain only letters and numbers")
    private String username;
    @Nullable
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private String address;
    private String contact;
    private String role;
}
