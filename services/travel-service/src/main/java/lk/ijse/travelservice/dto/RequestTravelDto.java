package lk.ijse.travelservice.dto;

import com.mongodb.lang.Nullable;
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
    @Pattern(regexp = "^(NEXT_)[0-9]{1,}$")
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
    @NotNull(message = "Payment Details fo travel package cannot be null")
    private PaymentDetailsDto paymentDetails;
    @Nullable
    private String remarks;
}
