package lk.ijse.travelservice.api;

import jakarta.validation.Valid;
import lk.ijse.travelservice.dto.RequestTravelDto;
import lk.ijse.travelservice.dto.ResponseTravelDto;
import lk.ijse.travelservice.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/travels")
public class TravelController {

    private final TravelService travelService;

    @Autowired
    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> bookingTravel(@RequestBody @Valid RequestTravelDto requestTravelDto) {
        return ResponseEntity.ok().body(travelService.bookingTravel(requestTravelDto));
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBooking(@RequestBody @Valid RequestTravelDto requestTravelDto) {
        return ResponseEntity.ok().body(travelService.updateBooking(requestTravelDto));
    }

    @GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseTravelDto>> getAllTravels() {
        return ResponseEntity.ok().body(travelService.getAllTravels());
    }

    @GetMapping(params = "packageId")
    public ResponseEntity<ResponseTravelDto> deleteTravelByPackageId(@RequestParam("packageId") String packageId) {
        return ResponseEntity.ok().body(travelService.deleteTravel(packageId));
    }
}
