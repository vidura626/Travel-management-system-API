package lk.ijse.travelservice.entity.embedded;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class GuideDetails {
    @Min(value = 0, message = "Guide Id of guide details should be positive")
    private long guideId;
    @Min(value = 0, message = "guide Fee For Day of guide details should be positive")
    private double guideFeeForDay;
}
