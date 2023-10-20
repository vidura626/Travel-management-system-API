package lk.ijse.vehicleservice.dto.embedded;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleImagesDto {
    private String frontImg;
    private String backImg;
    private String rearImg;
}
