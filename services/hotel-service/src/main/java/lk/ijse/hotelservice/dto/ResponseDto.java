package lk.ijse.hotelservice.dto;

import jakarta.validation.constraints.*;
import lk.ijse.hotelservice.dto.embedded.HotelContactDto;
import lk.ijse.hotelservice.dto.embedded.HotelFeeDto;
import lk.ijse.hotelservice.dto.embedded.HotelLocationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDto {
    @Id
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
    private HotelFeeDto fee;
    @Positive(message = "Cancellation fee cannot be less than 1")
    private double cancellationFee;
    @NotNull(message = "Hotel contacts cannot be null")
    private HotelContactDto contacts;
    @NotNull(message = "Hotel location cannot be null")
    private HotelLocationDto location;
    private Boolean isPetsAllowed;
}
