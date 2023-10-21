package lk.ijse.hotelservice.entity.embedded;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelLocation {
    @NotBlank(message = "Hotel location name cannot be blank")
    private String locationName;
    @NotBlank(message = "Google map location cannot be blank")
    private String googleMapLocation;
}
