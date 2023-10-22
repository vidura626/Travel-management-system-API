package lk.ijse.travelservice.exception;

public class TimeOutException extends RuntimeException {
    public TimeOutException(String message) {
        super(message);
    }
    public TimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
    public TimeOutException(Throwable cause) {
        super(cause);
    }
}
