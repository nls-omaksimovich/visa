package pl.oleggye.visa.appointment.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.oleggye.visa.appointment.application.port.in.ApplyForVisaCommand;
import pl.oleggye.visa.appointment.application.port.in.ApplyForVisaUseCase;
import pl.oleggye.visa.appointment.application.port.out.SubmitVisaApplicationPort;
import pl.oleggye.visa.appointment.domain.exception.RecordAlreadyExistsException;
import pl.oleggye.visa.appointment.domain.exception.VisaApplicationAlreadyExistsException;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;

import java.text.MessageFormat;

@Component
@AllArgsConstructor
public class ApplyForVisaService implements ApplyForVisaUseCase {

    private final SubmitVisaApplicationPort visaApplicationPort;

    @Override
    public VisaApplication applyForVisa(ApplyForVisaCommand applyForVisaCommand) {
        try {
            return visaApplicationPort.submit(applyForVisaCommand.application());
        } catch (RecordAlreadyExistsException exception) {
            String message = MessageFormat.format(
                    "VisaApplication with {0} already exists",
                    applyForVisaCommand.application().applicantName()
            );
            throw new VisaApplicationAlreadyExistsException(message, exception);
        }
    }
}
