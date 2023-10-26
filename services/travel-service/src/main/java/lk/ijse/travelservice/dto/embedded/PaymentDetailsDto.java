package lk.ijse.travelservice.dto.embedded;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
@Builder
public class PaymentDetailsDto {
    @Positive(message = "Paid Fee of payment details should be positive")
    private double paidFee;
    @Min(value = 0, message = "TotalFee Fee of payment details should be positive")
    private double totalFee;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Colombo")
    private Date date;
}
