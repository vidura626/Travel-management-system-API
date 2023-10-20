package lk.ijse.vehicleservice.dto.embedded;

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
    private String frontImg;
    private String backImg;
}
