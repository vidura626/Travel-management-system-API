package lk.ijse.travelservice.dto.embedded;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TravelDurationDto implements Serializable {
    @NotNull(message = "Start date cannot be null or empty")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Colombo")
    private Date startDate;
    @NotNull(message = "End date cannot be null or empty")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Colombo")
    private Date endDate;
    @Positive(message = "Day count should be positive number")
    private int dayCount;
    @Positive(message = "Night count should be positive number")
    private int nightCount;
}
