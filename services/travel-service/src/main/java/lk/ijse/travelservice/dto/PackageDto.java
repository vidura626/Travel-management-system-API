package lk.ijse.travelservice.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
@Builder
public class PackageDto {
    @NotBlank(message = "Package name of package info cannot be blank")
    private String packageName;
    @NotNull(message = "Hotel Ids of package info cannot be blank")
    private String[] hotelIds;
    @NotNull(message = "Vehicle Categories of package info cannot be blank")
    private String[] vehicleCategories;
}
