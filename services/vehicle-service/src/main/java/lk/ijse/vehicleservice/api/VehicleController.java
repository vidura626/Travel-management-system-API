package lk.ijse.vehicleservice.api;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.RequestDto;
import lk.ijse.vehicleservice.dto.ResponseDto;
import lk.ijse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(path = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> registerVehicle(@ModelAttribute @Valid RequestDto requestDto) {
        vehicleService.registerVehicle(requestDto);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> updateVehicle(@ModelAttribute @Valid RequestDto requestDto) {
        vehicleService.updateVehicle(requestDto);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseDto>> getAllVehicles() {
        return ResponseEntity.ok().body(vehicleService.findAllVehicles());
    }

    @DeleteMapping(path = "/delete/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteVehicle(@PathVariable(name = "username") long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok().body(null);
    }
}
