package pl.oleggye.visa;

import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.jdbc.Sql;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.infrastracture.in.web.dto.VisaApplicationRequest;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;

@Sql(scripts = "/sql/create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "/sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "/sql/drop.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class VisaApplicationIntegrationTest extends AbstractIntegrationTest {

    @Test
    @SneakyThrows
    void applyForVisa() {
        VisaApplicationRequest request = new VisaApplicationRequest(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                "testTimeSlot"
        );

        givenSpec()
                .body(request)
                .when().post("/visa/apply")
                .then().statusCode(HttpStatus.SC_CREATED);
    }

    @ParameterizedTest
    @MethodSource("provideApplicationStatuses")
    void checkRegistrationStatus(Long applicationId, ApplicationStatus expectedStatus) {
        givenSpec()
                .queryParam("applicationId", applicationId)
                .when()
                .get("/visa/check-status")
                .then().statusCode(HttpStatus.SC_OK)
                .body("applicationStatus", equalTo(expectedStatus.toString()));
    }

    static Stream<Arguments> provideApplicationStatuses() {
        return Stream.of(
                Arguments.of(111L, ApplicationStatus.SELECTED),
                Arguments.of(222L, ApplicationStatus.PENDING),
                Arguments.of(333L, ApplicationStatus.NON_SELECTED)
        );
    }
}
