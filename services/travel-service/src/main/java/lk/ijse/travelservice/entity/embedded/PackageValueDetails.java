package lk.ijse.travelservice.entity.embedded;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
@Builder
public class PackageValueDetails implements Serializable {
    @Positive(message = "Hotel day fees should be positive")
    @Min(0)
    private double hotelDayFees;
    @Positive(message = "Hotel Cancellation fees should be positive")
    @Min(0)
    private double hotelCancellationFee;
    @Positive(message = "Hotel night fees should be positive")
    @Min(0)
    private double hotelNightFees;
    @Positive(message = "Hotel fees should be positive")
    @Min(0)
    private double guideFees;
    @Positive(message = "Hotel fees should be positive")
    @Min(0)
    private double vehicleFees;
    @Min(value = 0, message = "Service Charges should be positive")
    private double serviceCharges;
}
