package lk.ijse.vehicleservice.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleFee {
    private double feeForDay;
    private double feeFor1km;
}
