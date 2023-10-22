package lk.ijse.travelservice.exception;

public class InvalidTravelDetailException extends RuntimeException {
    public InvalidTravelDetailException(String message) {
        super(message);
    }
    public InvalidTravelDetailException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidTravelDetailException(Throwable cause) {
        super(cause);
    }
}
