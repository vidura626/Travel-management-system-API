package lk.ijse.guideservice.entity.embedded;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NICorPassport {
    @Pattern(message = "NIC or Passport is present in invalid format",
            regexp = "^(?:[0-9]{9}[xXvV]|[0-9]{12}|[A-Z][0-9]{8})$")
    private String id;
    @NotNull(message = "Nic Front image cannot be null or blank")
    private String frontImg;
    @NotNull(message = "Nic Back image cannot be null or blank")
    private String backImg;
}
