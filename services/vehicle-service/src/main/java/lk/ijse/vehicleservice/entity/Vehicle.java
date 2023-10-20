package lk.ijse.vehicleservice.entity;

import jakarta.validation.constraints.*;
import lk.ijse.vehicleservice.entity.embedded.Driver;
import lk.ijse.vehicleservice.util.constants.FuelType;
import lk.ijse.vehicleservice.entity.embedded.VehicleImages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vehicle {
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
    private FuelType fuelType;
    private boolean isHybrid;
    private boolean isAuto;
    @Min(value = 1, message = "Seat capacity cannot be less than 1")
    @Max(value = 100, message = "Seat capacity cannot be greater than 100")
    private int seatCapacity;
    @NotNull(message = "Driver cannot be null")
    private Driver driver;
    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;
    @NotNull(message = "Vehicle images cannot be null")
    private VehicleImages images;
    @NotBlank(message = "Vehicle Type cannot be blank")
    private String vehicleType;
    @NotBlank(message = "Vehicle category cannot be blank")
    private String vehicleCategory;

}
