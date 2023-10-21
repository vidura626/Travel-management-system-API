package lk.ijse.hotelservice.dto.embedded;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelLocationDto {
    @NotBlank(message = "Hotel location name cannot be blank")
    private String locationName;
    @NotBlank(message = "Google map location cannot be blank")
    private String googleMapLocation;
}
