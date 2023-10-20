package lk.ijse.vehicleservice.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class VehicleCategory {
    @Id
    @Positive(message = "Category Id cannot be less than 1")
    private long id;
    @NotBlank(message = "Category cannot be blank")
    private String category;
}
