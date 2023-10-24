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
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/hotel")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(path = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> registerHotel(@ModelAttribute @Valid RequestDto requestDto) {
        hotelService.registerHotel(requestDto);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> updateHotel(@ModelAttribute @Valid RequestDto requestDto) {
        hotelService.updateHotel(requestDto);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseDto>> getAllHotels() {
        return ResponseEntity.ok().body(hotelService.findAllHotels());
    }

    @DeleteMapping(path = "/delete/{hotelId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteHotel(@PathVariable(name = "hotelId") long hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok().body(hotelId);
    }

    @GetMapping(path = "/{hotelId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getHotelById(@PathVariable(name = "hotelId") long hotelId) {
        return ResponseEntity.ok().body(hotelService.findHotelByHotelId(hotelId));
    }

    @GetMapping(path = "/checkHotelsByIds", params = "hotelId",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Long, String>> checkHotelsByIds(@RequestParam(name = "hotelId") List<String> hotelId) {
        return ResponseEntity.ok().body(hotelService.checkHotelsByIds(hotelId.stream().map(Long::parseLong).collect(toList())));
    }
}
