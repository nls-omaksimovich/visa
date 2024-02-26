package pl.oleggye.visa.appointment.infrastracture.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;

import java.time.LocalDateTime;

@Data
public class VisaApplicationResponse {
    private Long id;
    private String applicantName;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    private LocalDateTime requestedDateTime;

    private String timeslot;
    private ApplicationStatus applicationStatus;
}
