package lk.ijse.travelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PackageInfo implements Serializable {
    @Id
    @NotBlank(message = "Package name of package info cannot be blank")
    private String packageName;
    @NotBlank(message = "Hotel Ids of package info cannot be blank")
    private String hotelIds;
    @NotBlank(message = "Vehicle Categories of package info cannot be blank")
    private String vehicleCategories;
}
