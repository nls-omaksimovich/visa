package pl.oleggye.visa.appointment.infrastracture.out.persistance;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.oleggye.visa.appointment.application.port.out.LoadVisaApplicationPort;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;
import pl.oleggye.visa.appointment.infrastracture.out.persistance.repository.VisaApplicationRepository;

import java.util.List;
import java.util.Optional;

import static pl.oleggye.visa.appointment.infrastracture.out.persistance.mapper.VisaApplicationMapper.INSTANCE;

@Component
@AllArgsConstructor
public class LoadVisaApplicationAdapter implements LoadVisaApplicationPort {

    private final VisaApplicationRepository visaApplicationRepository;

    @Override
    public Optional<VisaApplication> findApplicationById(Long applicationId) {
        return visaApplicationRepository.findById(applicationId)
                .map(INSTANCE::visaApplicationEntityToVisaApplication);
    }

    @Override
    public List<VisaApplication> findAllApplications(ApplicationStatus applicationStatus) {
        return visaApplicationRepository.findByApplicationStatus(applicationStatus)
                .stream()
                .map(INSTANCE::visaApplicationEntityToVisaApplication)
                .toList();
    }
}
