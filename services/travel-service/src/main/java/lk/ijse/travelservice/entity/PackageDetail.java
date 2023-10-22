package lk.ijse.travelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
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
public class PackageDetail implements Serializable {
    @Id
    @NotEmpty(message = "Package Details Id cannot be null or empty")
    private Long packageDetailId;
    @NotEmpty(message = "Package name cannot be null or empty")
    private String packageName;
}
