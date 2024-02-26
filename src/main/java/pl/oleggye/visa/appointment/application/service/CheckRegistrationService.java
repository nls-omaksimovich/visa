package pl.oleggye.visa.appointment.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.oleggye.visa.appointment.application.port.in.CheckRegistrationQuery;
import pl.oleggye.visa.appointment.application.port.in.CheckRegistrationUseCase;
import pl.oleggye.visa.appointment.application.port.out.LoadVisaApplicationPort;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CheckRegistrationService implements CheckRegistrationUseCase {

    private final LoadVisaApplicationPort loadVisaApplicationPort;

    @Override
    public Optional<VisaApplication> checkRegistration(CheckRegistrationQuery checkRegistrationQuery) {
        return loadVisaApplicationPort.findApplicationById(checkRegistrationQuery.applicationId());
    }
}
