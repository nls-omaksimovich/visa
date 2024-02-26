package pl.oleggye.visa.appointment.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.oleggye.visa.appointment.application.port.in.ElectApplicationsCommand;
import pl.oleggye.visa.appointment.application.port.in.ElectApplicationsUseCase;
import pl.oleggye.visa.appointment.application.port.out.LoadVisaApplicationPort;
import pl.oleggye.visa.appointment.application.port.out.UpdateVisaApplicationPort;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Transactional
@Log
public class RandomElectApplicationsService implements ElectApplicationsUseCase {

    private final LoadVisaApplicationPort loadVisaApplicationPort;
    private final UpdateVisaApplicationPort updateVisaApplicationPort;
    private final Random rand = new Random();

    @Override
    public void electApplications(ElectApplicationsCommand electApplicationsCommand) {
        final var pendingApplications = loadVisaApplicationPort.findAllApplications(ApplicationStatus.PENDING);

        if (pendingApplications.isEmpty()) {
            log.info("There are no pending visa applications");
            return;
        }
        final var randomVisaApplication = selectRandomly(pendingApplications);
        final var selectedVisaApplication = updateVisaApplicationWithStatus(
                randomVisaApplication, ApplicationStatus.SELECTED
        );

        final List<VisaApplication> nonSelectedApplications = pendingApplications.stream()
                .filter(application -> application.getApplicationStatus() == ApplicationStatus.PENDING)
                .map(application -> updateVisaApplicationWithStatus(application, ApplicationStatus.NON_SELECTED))
                .collect(Collectors.toList());

        nonSelectedApplications.add(selectedVisaApplication);

        updateVisaApplicationPort.updateAll(nonSelectedApplications);
    }

    private VisaApplication selectRandomly(List<VisaApplication> visaApplications) {
        return visaApplications.get(rand.nextInt(0, visaApplications.size()));
    }

    private VisaApplication updateVisaApplicationWithStatus(
            VisaApplication visaApplication,
            ApplicationStatus applicationStatus
    ) {
        return new VisaApplication(
                visaApplication.id(),
                visaApplication.applicantName(),
                visaApplication.requestedDateTime(),
                visaApplication.timeslot(),
                applicationStatus
        );
    }
}
