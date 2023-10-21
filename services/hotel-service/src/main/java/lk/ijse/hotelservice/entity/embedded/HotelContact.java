package lk.ijse.hotelservice.entity.embedded;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelContact {
    @Pattern(message = "Invalid phone number", regexp = "^(\\+94|0)\\d{9}$")
    private String contact1;
    @Pattern(message = "Invalid phone number", regexp = "^(\\+94|0)\\d{9}$")
    private String contact2;
}
