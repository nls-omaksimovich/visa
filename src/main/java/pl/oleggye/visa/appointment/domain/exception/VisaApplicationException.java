package pl.oleggye.visa.appointment.domain.exception;

public abstract class VisaApplicationException extends RuntimeException {

    protected VisaApplicationException(String message) {
        super(message);
    }

    protected VisaApplicationException(String message, Exception cause) {
        super(message, cause);
    }
}
