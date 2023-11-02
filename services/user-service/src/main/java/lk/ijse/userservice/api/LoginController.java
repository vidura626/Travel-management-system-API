package lk.ijse.userservice.api;

import lk.ijse.userservice.dto.ResponseDto;
import lk.ijse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/user/login", method = RequestMethod.GET)
    public ResponseDto login(Authentication authentication) {
        return userService.findUserByUsername(authentication.getName());
    }
}
