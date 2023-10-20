package lk.ijse.vehicleservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestDto {
    private long vehicleId;
    private String name;
    private int fuelCapacity;
    private int fuelUsage;
    private String fuelType;
    private boolean isHybrid;
    private boolean isAuto;
    private int seatCapacity;
    private MultipartFile licenceFrontImg;
    private MultipartFile licenceBackImg;
    private String remarks;
    private MultipartFile frontImg;
    private MultipartFile backImg;
    private MultipartFile rearImg;
    private String vehicleType;
    private String category;
}
