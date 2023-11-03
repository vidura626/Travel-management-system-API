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

    @PutMapping(path = "/update",
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

    @DeleteMapping(path = "/delete/{guideId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteGuide(@PathVariable(name = "guideId") String guideId) {
        if (guideId == null) {
            throw new NullPointerException("Guide cannot be null");
        }
        guideService.deleteGuide(Long.parseLong(guideId));
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(path = "/{guideId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getGuideById(@PathVariable(name = "guideId") String guideId) {
        if (guideId == null) {
            throw new NullPointerException("Guide cannot be null");
        }
        return ResponseEntity.ok().body(guideService.findGuideByID(Long.parseLong(guideId)));
    }
}
