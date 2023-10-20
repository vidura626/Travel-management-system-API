package lk.ijse.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDto {
    private long vehicleId;
    private String name;
    private int fuelCapacity;
    private int fuelUsage;
    private String fuelType;
    private boolean isHybrid;
    private boolean isAuto;
    private int seatCapacity;
    private String licenceFrontImg;
    private String licenceBackImg;
    private String remarks;
    private String frontImg;
    private String backImg;
    private String rearImg;
    private String vehicleType;
    private String category;
}
