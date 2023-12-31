package lk.ijse.travelservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseTravelDto implements Serializable {
    @Pattern(regexp = "^(NEXT_)[0-9]{1,}$")
    private String packageID;
    @NotNull(message = "Package Detail of travel package cannot be null")
    private PackageDto packageInfo;
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
    @Min(value = 0, message = "Min value is 0 that represent no need guide for a package")
    private Integer needGuide;
    @Nullable
    private PackageValueDetailsDto packageValueDetails;
    @Min(value = 0, message = "Total should be positive value")
    @NotNull(message = "Payment Details fo travel package cannot be null")
    private PaymentDetailsDto paymentDetails;
    @Nullable
    private String remarks;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Colombo")
    private Date bookingDate;
}
