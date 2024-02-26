package pl.oleggye.visa.appointment.application.port.in;

import pl.oleggye.visa.appointment.domain.model.VisaApplication;

public record ApplyForVisaCommand(VisaApplication application) {
}
