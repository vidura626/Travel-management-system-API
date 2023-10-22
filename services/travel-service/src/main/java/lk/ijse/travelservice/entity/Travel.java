package lk.ijse.travelservice.entity;

import com.mongodb.lang.Nullable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lk.ijse.travelservice.entity.embedded.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Travel implements Serializable {
    @Id
    @Min(value = 1, message = "Hotel Id cannot be less than 1")
    @Max(value = 9999999999L, message = "Hotel Id cannot be greater than 9999999999")
    @Pattern(regexp = "^(NEXT)[0-9]{1,}$")
    private String packageID;
    @ManyToOne
    @NotNull(message = "Package Detail of travel package cannot be null")
    private Package packageInfo;
    @Embedded
    @NotNull(message = "Travel Duration of Travel Package cannot be null")
    private TravelDuration travelDuration;
    @Min(value = 0, message = "User id should be positive")
    private long userID;
    @Embedded
    @NotNull(message = "Vehicle Details of Travel Package cannot be null")
    private VehicleDetails vehicleDetails;
    @Embedded
    @Nullable
    private GuideDetails guideDetails;
    @NotNull(message = "Hotel Details of travel package cannot be null or empty")
    private HotelDetails hotelDetails;
    @NotNull(message = "MemberCount of Travel Package cannot be null")
    private MemberCount count;
    private Boolean withPets;
    @Embedded
    @Nullable
    private PackageValueDetails packageValueDetails;
    @Min(value = 0, message = "Total should be positive value")
    @NotNull(message = "Payment Details fo travel package cannot be null")
    private PaymentDetails paymentDetails;
    @Nullable
    private String remarks;
    @CreationTimestamp
    private Date bookingDate;
}
