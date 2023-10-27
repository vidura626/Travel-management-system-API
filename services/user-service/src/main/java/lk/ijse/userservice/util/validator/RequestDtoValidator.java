package lk.ijse.userservice.util.validator;

import lk.ijse.userservice.exception.RequestDtoValidationException;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public class RequestDtoValidator {

    @Named("userId")
    public long validate(long userId) throws RequestDtoValidationException {
        if (userId > 0) {
            return userId;
        }
        throw new RequestDtoValidationException("Invalid User Id");
    }

    @Named("password")
    public String validate(String password) {
        if (password == null) {
            return null;
        }
        return password;
//        if (password.matches("^(?=.*[A-Za-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
//            return password;
//        } else {
//            throw new RequestDtoValidationException("Password should contain at least 8 characters that contains minimum of one uppercase, one lowercase, one number and one special character");
//        }
    }

    @Named("gender")
    public String validateGender(String gender) {
        if (gender == null) throw new RequestDtoValidationException("Gender is required");
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
            return gender;
        } else {
            throw new RequestDtoValidationException("Gender should be male or female");
        }
    }
}
