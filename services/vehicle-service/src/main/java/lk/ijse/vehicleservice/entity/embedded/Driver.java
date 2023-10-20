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
public class Driver {
    @NotNull(message = "Driver name cannot be null")
    private String driverName;
    @NotNull(message = "Driver licence front image cannot be null")
    private String licenceFrontImg;
    @NotNull(message = "Driver licence back image cannot be null")
    private String licenceBackImg;
}
