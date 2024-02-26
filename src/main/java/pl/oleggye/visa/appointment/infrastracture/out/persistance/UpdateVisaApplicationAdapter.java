package pl.oleggye.visa.appointment.infrastracture.out.persistance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.oleggye.visa.appointment.application.port.out.UpdateVisaApplicationPort;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;
import pl.oleggye.visa.appointment.infrastracture.out.persistance.repository.VisaApplicationRepository;

import java.util.List;

import static pl.oleggye.visa.appointment.infrastracture.out.persistance.mapper.VisaApplicationMapper.INSTANCE;

@Component
@AllArgsConstructor
public class UpdateVisaApplicationAdapter implements UpdateVisaApplicationPort {

    private final VisaApplicationRepository visaApplicationRepository;

    @Override
    public VisaApplication update(VisaApplication visaApplication) {
        final var visaApplicationEntity = INSTANCE.visaApplicationToVisaApplicationEntity(visaApplication);
        return INSTANCE.visaApplicationEntityToVisaApplication(visaApplicationRepository.save(visaApplicationEntity));
    }

    @Override
    public void updateAll(List<VisaApplication> visaApplications) {
        final var visaApplicationEntities = visaApplications.stream()
                .map(INSTANCE::visaApplicationToVisaApplicationEntity)
                .toList();

        visaApplicationRepository.saveAll(visaApplicationEntities);
    }
}
