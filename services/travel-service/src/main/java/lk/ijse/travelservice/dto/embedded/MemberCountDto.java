package lk.ijse.travelservice.dto.embedded;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
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
public class MemberCountDto implements Serializable {
    @Min(value = 0, message = "Children count should be positive")
    private int children;
    @Min(value = 1, message = "Adults count should be positive")
    private int adults;
    @Min(value = 1, message = "Total count should be positive")
    private int total;
}
