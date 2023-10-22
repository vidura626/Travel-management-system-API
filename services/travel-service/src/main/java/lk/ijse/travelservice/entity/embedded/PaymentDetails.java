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
public class PaymentDetails {
    @Positive(message = "Paid Fee of payment details should be positive")
    private double paidFee;
    @Min(value = 0, message = "TotalFee Fee of payment details should be positive")
    private double totalFee;
}
