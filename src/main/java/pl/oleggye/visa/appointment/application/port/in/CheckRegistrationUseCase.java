package pl.oleggye.visa.appointment.application.port.in;

import pl.oleggye.visa.appointment.domain.model.VisaApplication;

import java.util.Optional;

public interface CheckRegistrationUseCase {
    Optional<VisaApplication> checkRegistration(CheckRegistrationQuery checkRegistrationQuery);
}
