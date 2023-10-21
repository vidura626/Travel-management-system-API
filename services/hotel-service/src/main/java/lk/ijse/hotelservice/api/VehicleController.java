package lk.ijse.hotelservice.api;

import jakarta.validation.Valid;
import lk.ijse.hotelservice.dto.RequestDto;
import lk.ijse.hotelservice.dto.ResponseDto;
import lk.ijse.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {
    private final HotelService hotelService;

    @Autowired
    public VehicleController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(path = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> registerVehicle(@ModelAttribute @Valid RequestDto requestDto) {
        hotelService.registerVehicle(requestDto);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> updateVehicle(@ModelAttribute @Valid RequestDto requestDto) {
        hotelService.updateVehicle(requestDto);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseDto>> getAllVehicles() {
        return ResponseEntity.ok().body(hotelService.findAllVehicles());
    }

    @DeleteMapping(path = "/delete/{vehicleId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteVehicle(@PathVariable(name = "vehicleId") long vehicleId) {
        hotelService.deleteVehicle(vehicleId);
        return ResponseEntity.ok().body(vehicleId);
    }
}
