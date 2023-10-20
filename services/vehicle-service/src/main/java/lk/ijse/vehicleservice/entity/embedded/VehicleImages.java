package lk.ijse.vehicleservice.entity.embedded;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleImages {
    @NotNull(message = "Front image cannot be null")
    private String frontImg;
    @NotNull(message = "Back image cannot be null")
    private String backImg;
    @NotNull(message = "Rear image cannot be null")
    private String rearImg;
}
