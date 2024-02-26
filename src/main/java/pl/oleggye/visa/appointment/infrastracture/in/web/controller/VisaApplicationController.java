package pl.oleggye.visa.appointment.infrastracture.in.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.oleggye.visa.appointment.application.port.in.ApplyForVisaCommand;
import pl.oleggye.visa.appointment.application.port.in.ApplyForVisaUseCase;
import pl.oleggye.visa.appointment.application.port.in.CheckRegistrationQuery;
import pl.oleggye.visa.appointment.application.port.in.CheckRegistrationUseCase;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.infrastracture.in.web.dto.VisaApplicationRequest;
import pl.oleggye.visa.appointment.infrastracture.in.web.dto.VisaApplicationResponse;

import static pl.oleggye.visa.appointment.infrastracture.in.web.VisaApplicationMapper.INSTANCE;

@RestController
@AllArgsConstructor
public class VisaApplicationController {

    private final ApplyForVisaUseCase applyForVisaUseCase;
    private final CheckRegistrationUseCase checkRegistrationUseCase;

    @PostMapping("/visa/apply")
    @ResponseStatus(HttpStatus.CREATED)
    public VisaApplicationResponse applyForVisa(@RequestBody @Valid VisaApplicationRequest request) {
        final var visaApplication = INSTANCE.visaApplicationRequestToVisaApplication(
                request,
                ApplicationStatus.PENDING
        );
        final var appliedVisaApplication = applyForVisaUseCase.applyForVisa(new ApplyForVisaCommand(visaApplication));

        return INSTANCE.visaApplicationToVisaApplicationResponse(appliedVisaApplication);
    }

    @GetMapping("/visa/check-status")
    public ResponseEntity<VisaApplicationResponse> checkRegistrationStatus(
            @RequestParam("applicationId") Long applicationId
    ) {
        final var checkRegistrationQuery = new CheckRegistrationQuery(applicationId);

        return checkRegistrationUseCase.checkRegistration(checkRegistrationQuery)
                .map(INSTANCE::visaApplicationToVisaApplicationResponse)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
