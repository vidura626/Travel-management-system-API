package lk.ijse.userservice.dto.embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NICorPassportDto {
    private String id;
    private MultipartFile frontImg;
    private MultipartFile backImg;
}
