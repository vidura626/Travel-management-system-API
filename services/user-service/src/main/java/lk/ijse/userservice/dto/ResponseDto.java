package lk.ijse.userservice.dto;

import lk.ijse.userservice.dto.embedded.NICorPassportDto;
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
public class ResponseDto {
    private long userId;
    private String name;
    private String gender;
    private Date dob;
    private MultipartFile proPic;
    private String remarks;
    private NICorPassportDto niCorPassportDto;
    private Date regDate;
    private String username;
    private String email;
    private String address;
    private String contact;
    private String role;
}
