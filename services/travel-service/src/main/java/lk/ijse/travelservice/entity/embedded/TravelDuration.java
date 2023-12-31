package lk.ijse.travelservice.entity.embedded;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
@Builder
public class TravelDuration implements Serializable {
    @NotNull(message = "Start date cannot be null or empty")
    private Date startDate;
    @NotNull(message = "End date cannot be null or empty")
    private Date endDate;
    @Positive(message = "Day count should be positive number")
    private int dayCount;
    @Positive(message = "Night count should be positive number")
    private int nightCount;
}
