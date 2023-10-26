package lk.ijse.travelservice.dto.hotel;

import jakarta.validation.constraints.*;
import lk.ijse.travelservice.dto.hotel.embedded.HotelContact;
import lk.ijse.travelservice.dto.hotel.embedded.HotelFee;
import lk.ijse.travelservice.dto.hotel.embedded.HotelLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Hotel {
    @Min(value = 1, message = "Hotel Id cannot be less than 1")
    @Max(value = 9999999999L, message = "Hotel Id cannot be greater than 9999999999")
    private long hotelId;
    @NotBlank(message = "Hotel name cannot be blank")
    @Size(min = 3, message = "Hotel name should be at least 3 characters")
    private String hotelName;
    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;
    @Min(1)
    @Max(5)
    @Positive(message = "Rating cannot be less than 1 or greater than 5")
    private int hotelCategory;
    @NotNull(message = "Hotel fee cannot be null")
    private HotelFee fee;
    @Positive(message = "Cancellation fee cannot be less than 1")
    private double cancellationFee;
    @NotNull(message = "Hotel contacts cannot be null")
    private HotelContact contacts;
    @NotNull(message = "Hotel location cannot be null")
    private HotelLocation location;
    private Boolean isPetsAllowed;
}
