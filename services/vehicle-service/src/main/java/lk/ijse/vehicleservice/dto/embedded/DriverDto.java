package lk.ijse.vehicleservice.dto.embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DriverDto {
    private String name;
    private String licenceFrontImg;
    private String licenceBackImg;
}
