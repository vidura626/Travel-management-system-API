package lk.ijse.guideservice.api;

import jakarta.validation.Valid;
import lk.ijse.guideservice.dto.RequestDto;
import lk.ijse.guideservice.dto.ResponseDto;
import lk.ijse.guideservice.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/guide")
public class GuideController {
    private final GuideService guideService;

    @Autowired
    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @PostMapping(path = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> registerGuide(@ModelAttribute @Valid RequestDto requestDto) {
        guideService.registerGuide(requestDto);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(path = "/update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> updateGuide(@ModelAttribute @Valid RequestDto requestDto) {
        guideService.updateGuide(requestDto);
        return ResponseEntity.ok().body(null);
    }


    @GetMapping(path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseDto>> getAllGuides() {
        return ResponseEntity.ok().body(guideService.findAllGuides());
    }

    @DeleteMapping(path = "/delete/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteGuide(@PathVariable(name = "username") String username) {
        if (username == null) {
            throw new NullPointerException("Username cannot be null");
        }
        guideService.deleteGuide(Long.parseLong(username));
        return ResponseEntity.ok().body(null);
    }
}
