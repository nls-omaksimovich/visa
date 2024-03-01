package pl.oleggye.visa.appointment.domain.exception;

public class VisaApplicationAlreadyExistsException extends VisaApplicationException {

    public VisaApplicationAlreadyExistsException(String message) {
        super(message);
    }

    public VisaApplicationAlreadyExistsException(String message, Exception cause) {
        super(message, cause);
    }
}
