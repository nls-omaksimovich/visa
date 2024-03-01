package pl.oleggye.visa.appointment.application.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pl.oleggye.visa.appointment.application.port.in.ApplyForVisaCommand;
import pl.oleggye.visa.appointment.application.port.out.SubmitVisaApplicationPort;
import pl.oleggye.visa.appointment.domain.exception.RecordAlreadyExistsException;
import pl.oleggye.visa.appointment.domain.exception.VisaApplicationAlreadyExistsException;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(classes = ApplyForVisaService.class)
class ApplyForVisaServiceTest {
    @Autowired
    private ApplyForVisaService applyForVisaService;

    @MockBean
    private SubmitVisaApplicationPort submitVisaApplicationPort;

    @MockBean
    private ApplyForVisaCommand applyForVisaCommand;

    @MockBean
    private VisaApplication visaApplication;

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(submitVisaApplicationPort, applyForVisaCommand, visaApplication);
    }

    @Test
    void check_contextStarts() {
        assertThat(applyForVisaService).isNotNull();
    }

    @Test
    void applyForVisa_shouldThrowExceptionWhenApplicationIsAlreadyExists() {
        when(applyForVisaCommand.application()).thenReturn(visaApplication);
        when(submitVisaApplicationPort.submit(eq(visaApplication))).thenThrow(new RecordAlreadyExistsException());
        when(visaApplication.applicantName()).thenReturn("existingTestApplication");

        assertThatThrownBy(() -> applyForVisaService.applyForVisa(applyForVisaCommand))
                .withFailMessage("Invalid exception")
                .isInstanceOf(VisaApplicationAlreadyExistsException.class)
                .hasMessage("VisaApplication with existingTestApplication already exists");

        verify(applyForVisaCommand, times(2)).application();
        verify(submitVisaApplicationPort, only()).submit(eq(visaApplication));
        verify(visaApplication, only()).applicantName();
    }
}
