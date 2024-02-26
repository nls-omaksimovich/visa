package pl.oleggye.visa.appointment.infrastracture.out.persistance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.oleggye.visa.appointment.application.port.out.SubmitVisaApplicationPort;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;
import pl.oleggye.visa.appointment.infrastracture.out.persistance.entity.VisaApplicationEntity;
import pl.oleggye.visa.appointment.infrastracture.out.persistance.repository.VisaApplicationRepository;

import static pl.oleggye.visa.appointment.infrastracture.out.persistance.mapper.VisaApplicationMapper.INSTANCE;

@Component
@AllArgsConstructor
public class SubmitVisaApplicationAdapter implements SubmitVisaApplicationPort {

    private final VisaApplicationRepository visaApplicationRepository;

    @Override
    public VisaApplication submit(VisaApplication application) {
        VisaApplicationEntity entity = INSTANCE.visaApplicationToVisaApplicationEntity(application);
        VisaApplicationEntity savedEntity = visaApplicationRepository.save(entity);
        return INSTANCE.visaApplicationEntityToVisaApplication(savedEntity);
    }
}
