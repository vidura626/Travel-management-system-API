package lk.ijse.hotelservice.dto.embedded;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelFeeDto {
    @Positive(message = "Fee option cannot be less than 1")
    private double feeOpt1;
    @Positive(message = "Fee option cannot be less than 1")
    private double feeOpt2;
    @Positive(message = "Fee option cannot be less than 1")
    private double feeOpt3;
    @Positive(message = "Fee option cannot be less than 1")
    private double feeOpt4;
}
