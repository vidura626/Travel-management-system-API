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
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> registerUser(@RequestBody @Valid RequestDto requestDto) {
        userService.registerUser(requestDto);
        return null;
    }

    @PutMapping(path = "/update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto updateUser(@RequestBody @Valid RequestDto requestDto) {

//        TODO: Validation

//        TODO: Save to DB

//        TODO: Return
        return null;
    }

    @PatchMapping(path = "/change/pwd",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto changePassword(String username, String password) {
//        TODO: Validation

//        TODO: Update DB

//        TODO: Return
        return null;
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
