package pl.oleggye.visa.appointment.infrastracture.in.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.oleggye.visa.appointment.application.port.in.ApplyForVisaCommand;
import pl.oleggye.visa.appointment.application.port.in.ApplyForVisaUseCase;
import pl.oleggye.visa.appointment.application.port.in.CheckRegistrationUseCase;
import pl.oleggye.visa.appointment.domain.exception.VisaApplicationAlreadyExistsException;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;
import pl.oleggye.visa.appointment.infrastracture.in.web.dto.VisaApplicationRequest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VisaApplicationController.class)
class VisaApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ApplyForVisaUseCase applyForVisaUseCase;
    @MockBean
    private CheckRegistrationUseCase checkRegistrationUseCase;
    @Mock
    private VisaApplication visaApplication;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(applyForVisaUseCase, checkRegistrationUseCase);
    }

    @Test
    void applyForVisa_WithMissingRequiredFields_ShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/visa/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print()) // This will print the request and response details
                .andExpect(status().isBadRequest());
    }

    @Test
    void applyForVisa_WithDuplicateApplication_ShouldReturnBadRequest() throws Exception {
        VisaApplicationRequest request = new VisaApplicationRequest(
                "John Doe",
                LocalDateTime.now(),
                "morning"
        );

        // Simulate the first application
        when(applyForVisaUseCase.applyForVisa(any(ApplyForVisaCommand.class))).thenThrow(VisaApplicationAlreadyExistsException.class);

        // Attempt to apply with the same details again
        mockMvc.perform(post("/visa/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print()) // This will print the request and response details
                .andExpect(status().isBadRequest());

        verify(applyForVisaUseCase, times(1)).applyForVisa(any(ApplyForVisaCommand.class));
    }

    @Test
    void applyForVisa_WhenApplicationNameSizeExceeded_ShouldReturnBadRequest() throws Exception {
        VisaApplicationRequest request = new VisaApplicationRequest(
                new String(new char[41]).replace('\0', 'a'),// more than max length that is 40
                LocalDateTime.now(),
                "late night"
        );

        mockMvc.perform(post("/visa/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print()) // This will print the request and response details
                .andExpect(status().isBadRequest());

        verify(applyForVisaUseCase, never()).applyForVisa(any(ApplyForVisaCommand.class));
    }

    @ParameterizedTest
    @MethodSource("provideVisaApplicationsAndResponseStatuses")
    @SneakyThrows
    void applyForVisaSuccessfully(VisaApplicationRequest request, ResultMatcher statusMatcher) {
        when(applyForVisaUseCase.applyForVisa(any(ApplyForVisaCommand.class))).thenReturn(visaApplication);

        mockMvc.perform(post("/visa/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print()) // This will print the request and response details
                .andExpect(statusMatcher);

        verify(applyForVisaUseCase, times(1)).applyForVisa(any(ApplyForVisaCommand.class));
    }

    static Stream<Arguments> provideVisaApplicationsAndResponseStatuses() {
        return Stream.of(
                Arguments.of(
                        new VisaApplicationRequest(
                                "John Doe",
                                LocalDateTime.now(),
                                "morning"
                        ),
                        status().isCreated()
                ),
                Arguments.of(
                        new VisaApplicationRequest(
                                "John Doe",
                                LocalDateTime.now(),
                                "afternoon"
                        ),
                        status().isCreated()
                ),
                Arguments.of(
                        new VisaApplicationRequest(
                                new String(new char[40]).replace('\0', 'a'),// Assuming 40 is max length
                                LocalDateTime.now(),
                                "evening"
                        ),
                        status().isCreated()
                ),
                Arguments.of(
                        new VisaApplicationRequest(
                                "José María",
                                LocalDateTime.now(),
                                "night"
                        ),
                        status().isCreated()
                )
        );
    }
}
