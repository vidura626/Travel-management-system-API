package lk.ijse.travelservice.api;

import jakarta.validation.Valid;
import lk.ijse.travelservice.dto.TravelPackageDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/travels")
public class TravelController {

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createPackage(@RequestBody @Valid TravelPackageDto travelPackageDto) {


        return null;
    }
}
