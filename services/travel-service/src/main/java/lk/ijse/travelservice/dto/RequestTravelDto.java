package lk.ijse.travelservice.dto;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lk.ijse.travelservice.dto.embedded.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestTravelDto implements Serializable {
    @Min(value = 1, message = "Hotel Id cannot be less than 1")
    @Max(value = 9999999999L, message = "Hotel Id cannot be greater than 9999999999")
    @Pattern(regexp = "^(NEXT)[0-9]{1,}$")
    private String packageID;
    @NotNull(message = "Package Detail of travel package cannot be null")
    private String packageInfo;
    @NotNull(message = "Travel Duration of Travel Package cannot be null")
    private TravelDurationDto travelDuration;
    @Min(value = 0, message = "User id should be positive")
    private long userID;
    @NotNull(message = "Vehicle Details of Travel Package cannot be null")
    private VehicleDetailsDto vehicleDetails;
    @Nullable
    private GuideDetailsDto guideDetails;
    @NotNull(message = "Hotel Details of travel package cannot be null or empty")
    private HotelDetailsDto hotelDetails;
    @NotNull(message = "MemberCount of Travel Package cannot be null")
    private MemberCountDto count;
    private Boolean withPets;
    @Nullable
    private PackageValueDetailsDto packageValueDetails;
    @Min(value = 0, message = "Total should be positive value")
    @NotNull(message = "Payment Details fo travel package cannot be null")
    private PaymentDetailsDto paymentDetails;
    @Nullable
    private String remarks;
}
