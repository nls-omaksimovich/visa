package pl.oleggye.visa.appointment.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

public record VisaApplication(
        Long id,
        String applicantName,
        LocalDateTime requestedDateTime,
        String timeslot,

        @Getter
        ApplicationStatus applicationStatus
) {
}
