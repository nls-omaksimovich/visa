package pl.oleggye.visa.appointment.application.port.out;

import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;

import java.util.List;
import java.util.Optional;

public interface LoadVisaApplicationPort {
    Optional<VisaApplication> findApplicationById(Long id);

    List<VisaApplication> findAllApplications(ApplicationStatus applicationStatus);
}
