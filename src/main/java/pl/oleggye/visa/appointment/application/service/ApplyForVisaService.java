package pl.oleggye.visa.appointment.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.oleggye.visa.appointment.application.port.in.ApplyForVisaCommand;
import pl.oleggye.visa.appointment.application.port.in.ApplyForVisaUseCase;
import pl.oleggye.visa.appointment.application.port.out.SubmitVisaApplicationPort;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;

@Component
@AllArgsConstructor
public class ApplyForVisaService implements ApplyForVisaUseCase {

    private final SubmitVisaApplicationPort visaApplicationPort;

    @Override
    public VisaApplication applyForVisa(ApplyForVisaCommand applyForVisaCommand) {
        return visaApplicationPort.submit(applyForVisaCommand.application());
    }
}
