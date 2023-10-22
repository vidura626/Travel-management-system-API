package lk.ijse.travelservice.entity.embedded;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
@Builder
public class VehicleDetails {
    @Positive(message = "Vehicle id of Vehicle Details should be positive")
    private long vehicleId;
    @Positive(message = "vehicleDayFee of Vehicle Details should be positive")
    private double vehicleDayFee;
    @Positive(message = "vehicleKMFee of Vehicle Details should be positive")
    private double vehicleKMFee;
    @Min(value = 0, message = "kmCount of Vehicle Details should be positive")
    private double kmCount;
}
