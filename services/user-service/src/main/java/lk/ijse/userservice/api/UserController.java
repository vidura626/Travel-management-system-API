package lk.ijse.userservice.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lk.ijse.userservice.dto.RequestDto;
import lk.ijse.userservice.dto.ResponseDto;
import lk.ijse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> registerUser(@ModelAttribute @Valid RequestDto requestDto) {
        userService.registerUser(requestDto);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> updateUser(@ModelAttribute @Valid RequestDto requestDto) {
        userService.updateUser(requestDto);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto login(@RequestBody RequestDto requestDto) {
//        TODO: Validation

//        TODO: Check Login

//        TODO: Return
        return null;
    }

    @GetMapping(path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @DeleteMapping(path = "/delete/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable(name = "username") String username) {
        if (username == null) {
            throw new NullPointerException("Username cannot be null");
        }
        userService.deleteUser(username);
        return ResponseEntity.ok().body(null);
    }
}
