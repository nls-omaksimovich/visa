package pl.oleggye.visa.appointment.infrastracture.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record VisaApplicationRequest(
        @NotNull
        @Size(min = ValidationConstants.APPLICANT_NAME_MIN_LENGTH, max = ValidationConstants.APPLICANT_NAME_MAX_LENGTH)
        String applicantName,
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
        LocalDateTime requestedDateTime,
        @NotNull
        String timeslot
) {
    private static class ValidationConstants {
        public static final int APPLICANT_NAME_MIN_LENGTH = 1;
        public static final int APPLICANT_NAME_MAX_LENGTH = 40;
    }
}
