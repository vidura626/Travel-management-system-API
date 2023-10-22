package lk.ijse.vehicleservice.dto.embedded;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleFeeDto {
    @Positive(message = "Day fee should be positive")
    private double feeForDay;
    @Positive(message = "1KM fee should be positive")
    private double feeFor1km;
}
