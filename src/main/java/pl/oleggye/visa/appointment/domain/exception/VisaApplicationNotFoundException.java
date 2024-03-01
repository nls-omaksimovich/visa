package pl.oleggye.visa.appointment.domain.exception;

public class VisaApplicationNotFoundException extends VisaApplicationException {
    protected VisaApplicationNotFoundException(String message) {
        super(message);
    }

    protected VisaApplicationNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
