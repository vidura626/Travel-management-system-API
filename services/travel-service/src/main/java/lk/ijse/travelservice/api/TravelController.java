package lk.ijse.travelservice.api;

import jakarta.validation.Valid;
import lk.ijse.travelservice.dto.RequestTravelDto;
import lk.ijse.travelservice.dto.ResponseTravelDto;
import lk.ijse.travelservice.service.TravelService;
import lk.ijse.travelservice.util.constants.TravelStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping(path = "/all")
    public ResponseEntity<List<ResponseTravelDto>> getAllTravels() {
        return ResponseEntity.ok().body(travelService.getAllTravels());
    }

    @GetMapping(params = "packageId")
    public ResponseEntity<ResponseTravelDto> findTravelByPackageId(@RequestParam("packageId") String packageId) {
        return ResponseEntity.ok().body(travelService.findTravelByPackageId(packageId));
    }
    @GetMapping(path = "hasActiveTravels")
    public ResponseEntity<Map<TravelStatus, Boolean>> hasActiveTravels(@RequestParam("userId") String userId) {
        return ResponseEntity.ok().body(travelService.hasActiveTravels(Long.parseLong(userId)));
    }

    @DeleteMapping(path = "delete/{travelId}")
    public ResponseEntity<ResponseTravelDto> deleteTravel(@PathVariable("travelId") String travelId) {
        return ResponseEntity.ok().body(travelService.deleteTravel(travelId));
    }
    @DeleteMapping(path = "deleteByUserId/{userId}")
    public ResponseEntity<Boolean> deleteByUserId(@PathVariable("userId") String userId) {
        travelService.deleteByUserId(Long.parseLong(userId));
        return ResponseEntity.ok().body(true);
    }
}
