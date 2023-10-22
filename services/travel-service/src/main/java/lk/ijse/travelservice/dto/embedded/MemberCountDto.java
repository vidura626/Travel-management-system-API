package lk.ijse.travelservice.dto.embedded;

import jakarta.persistence.Embeddable;
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
    private int children;
    private int adults;
    private int total;
}
