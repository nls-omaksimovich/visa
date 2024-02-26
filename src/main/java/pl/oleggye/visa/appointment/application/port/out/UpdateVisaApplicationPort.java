package pl.oleggye.visa.appointment.application.port.out;

import pl.oleggye.visa.appointment.domain.model.VisaApplication;

import java.util.List;

public interface UpdateVisaApplicationPort {
    VisaApplication update(VisaApplication visaApplication);

    void updateAll(List<VisaApplication> visaApplications);
}
