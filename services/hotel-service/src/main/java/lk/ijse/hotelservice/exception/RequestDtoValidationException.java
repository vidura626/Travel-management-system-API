package lk.ijse.hotelservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestDtoValidationException extends RuntimeException {
    public RequestDtoValidationException(String message) {
        super(message);
    }
}
