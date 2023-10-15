package lk.ijse.userservice.dto.embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NICorPassportDto {
    private String id;
    private byte[] frontImg;
    private byte[] backImg;
}
