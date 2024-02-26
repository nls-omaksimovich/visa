package pl.oleggye.visa.appointment.application.port.out;

import pl.oleggye.visa.appointment.domain.model.VisaApplication;

public interface SubmitVisaApplicationPort {
    VisaApplication submit(VisaApplication application);
}
