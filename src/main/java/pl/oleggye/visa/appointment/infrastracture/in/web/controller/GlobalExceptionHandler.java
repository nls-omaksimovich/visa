package pl.oleggye.visa.appointment.infrastracture.in.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.oleggye.visa.appointment.domain.exception.VisaApplicationAlreadyExistsException;
import pl.oleggye.visa.appointment.domain.exception.VisaApplicationNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VisaApplicationAlreadyExistsException.class)
    public ResponseEntity<Object> handleVisaApplicationAlreadyExistsException(
            VisaApplicationAlreadyExistsException exception
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(problemDetail);
    }

    @ExceptionHandler(VisaApplicationNotFoundException.class)
    public ResponseEntity<Object> handleVisaApplicationNotFoundException(
            VisaApplicationNotFoundException exception
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }
}
