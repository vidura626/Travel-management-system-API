package lk.ijse.userservice.api;

import lk.ijse.userservice.dto.request.RequestDto;
import lk.ijse.userservice.dto.response.ResponseDto;
import lk.ijse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto registerUser(@RequestBody RequestDto requestDto) {

//        TODO: Validation

//        TODO: Save to DB

//        TODO: Return
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto registerUser(@RequestBody RequestDto requestDto) {

//        TODO: Validation

//        TODO: Save to DB

//        TODO: Return
    }

    @PatchMapping(path = "/change/pwd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto changePassword(String username, String password) {
//        TODO: Validation

//        TODO: Update DB

//        TODO: Return
        return null;
    }

    @PatchMapping(path = "/change/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto changeEmail(String username, String email) {
//        TODO: Validation

//        TODO: Update DB

//        TODO: Return
        return null;
    }

    @PatchMapping(path = "/change/contact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto changeContact(String username, String contact) {
//        TODO: Validation

//        TODO: Update DB

//        TODO: Return
        return null;
    }

    @GetMapping("/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto login(@RequestBody RequestDto requestDto) {
//        TODO: Validation

//        TODO: Check Login

//        TODO: Return
        return null;
    }

    @GetMapping("/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto getAllUsers() {
//        TODO: Get All users

//        TODO: Return
        return null;
    }

}
