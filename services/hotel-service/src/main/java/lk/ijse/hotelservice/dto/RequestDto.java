package lk.ijse.hotelservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestDto {
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
    @Positive(message = "Fee option cannot be less than 1")
    private double feeOpt1;
    @Positive(message = "Fee option cannot be less than 1")
    private double feeOpt2;
    @Positive(message = "Fee option cannot be less than 1")
    private double feeOpt3;
    @Positive(message = "Fee option cannot be less than 1")
    private double feeOpt4;
    @Positive(message = "Cancellation fee cannot be less than 1")
    private double cancellationFee;
    @NotNull(message = "Hotel contacts cannot be null")
    @Pattern(message = "Invalid phone number", regexp = "^(\\+94|0)\\d{9}$")
    private String contact1;
    @Pattern(message = "Invalid phone number", regexp = "^(\\+94|0)\\d{9}$")
    private String contact2;
    @NotBlank(message = "Hotel location name cannot be blank")
    private String locationName;
    @NotBlank(message = "Google map location cannot be blank")
    private String googleMapLocation;
    private Boolean isPetsAllowed;
}
