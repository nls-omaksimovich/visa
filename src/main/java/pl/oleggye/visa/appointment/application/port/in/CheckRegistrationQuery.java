package pl.oleggye.visa.appointment.application.port.in;

import jakarta.validation.constraints.NotNull;

public record CheckRegistrationQuery(@NotNull Long applicationId) {
}
