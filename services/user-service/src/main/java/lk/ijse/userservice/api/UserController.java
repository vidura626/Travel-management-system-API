package lk.ijse.userservice.api;

import jakarta.validation.Valid;
import lk.ijse.userservice.dto.RequestDto;
import lk.ijse.userservice.dto.ResponseDto;
import lk.ijse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;

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

    @PatchMapping(path = "/change/pwd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> changePassword(String username, String password) {
        if (username == null || password == null) {
            throw new NullPointerException("Username or password cannot be null");
        } else {
            return ResponseEntity.ok().body(userService.changePassword(username, password));
        }
    }

    @PatchMapping(path = "/change/email",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto changeEmail(String username, String email) {
//        TODO: Validation

//        TODO: Update DB

//        TODO: Return
        return null;
    }

    @PatchMapping(path = "/change/contact",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto changeContact(String username, String contact) {
//        TODO: Validation

//        TODO: Update DB

//        TODO: Return
        return null;
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
    public ResponseDto getAllUsers() {
//        TODO: Get All users

//        TODO: Return
        return null;
    }

    @DeleteMapping(path = "/delete",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto deleteUser(String username) {
//        TODO: Delete user

//        TODO: Return

        return null;
    }
}
