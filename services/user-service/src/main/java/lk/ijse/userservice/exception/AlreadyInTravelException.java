package lk.ijse.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyInTravelException extends RuntimeException {
    public AlreadyInTravelException(String message) {
        super(message);
    }
}
