package lk.ijse.userservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {
    @NotNull
    String name;
    @NotNull
    MultipartFile proPic;
    @NotNull
    MultipartFile frontImg;
}
