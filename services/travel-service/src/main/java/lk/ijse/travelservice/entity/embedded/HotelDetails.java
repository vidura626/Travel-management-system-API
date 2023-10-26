package lk.ijse.travelservice.entity.embedded;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
@Builder
public class HotelDetails {
    @Min(value = 0, message = "Hotel Id of hotel details should be positive")
    private long hotelId;
    @Min(value = 0, message = "Hotel Fee of hotel details should be positive")
    private double hotelFee;
    @Min(value = 0, message = "Cancellation fee of hotel details should be positive")
    private double hotelCancellationFee;
}
