package lk.ijse.vehicleservice.dto;

import jakarta.validation.constraints.*;
import lk.ijse.vehicleservice.util.constants.FuelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestDto {
    @Min(value = 1, message = "Vehicle Id cannot be less than 1")
    @Max(value = 9999999999L, message = "Vehicle Id cannot be greater than 9999999999")
    private long vehicleId;
    @NotBlank(message = "Vehicle name cannot be blank")
    @Size(min = 3, message = "Vehicle name should be at least 3 characters")
    private String name;
    @Min(value = 1, message = "FuelUsage cannot be less than 1")
    @Max(value = 500, message = "FuelUsage cannot be greater than 500")
    private int fuelCapacity;
    @Min(value = 1, message = "fuel Usage cannot be less than 1")
    @Max(value = 100, message = "fuel Usage cannot be greater than 100")
    private int fuelUsage;
    private Boolean isHybrid;
    private Boolean isAuto;
    private FuelType fuelType;
    @Min(value = 1, message = "Seat capacity cannot be less than 1")
    @Max(value = 100, message = "Seat capacity cannot be greater than 100")
    private int seatCapacity;
    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;
    @NotBlank(message = "Driver name cannot be blank")
    private String driverName;
    @NotNull(message = "Licence Front Image cannot be null")
    private MultipartFile licenceFrontImg;
    @NotNull(message = "Licence Back Image cannot be null")
    private MultipartFile licenceBackImg;
    @NotNull(message = "Vehicle Front Image cannot be null")
    private MultipartFile frontImg;
    @NotNull(message = "Vehicle Back Image cannot be null")
    private MultipartFile backImg;
    @NotNull(message = "Vehicle Rear Image cannot be null")
    private MultipartFile rearImg;
    @NotBlank(message = "Vehicle Type cannot be blank")
    private String vehicleType;
    @NotBlank(message = "Vehicle category cannot be blank")
    private String vehicleCategory;
    @Positive(message = "Day fee should be positive")
    private double feeForDay;
    @Positive(message = "1KM fee should be positive")
    private double feeFor1km;
}
