package pl.oleggye.visa.appointment.infrastracture.out.persistance.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.infrastracture.out.persistance.entity.VisaApplicationEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        "spring.test.database.replace=none"
})
class VisaApplicationRepositoryTest {
    @Autowired
    private VisaApplicationRepository visaApplicationRepository;

    @Test
    void findAllWhereApplicationStatusIs() {
        List<VisaApplicationEntity> applications = visaApplicationRepository.findAllWhereApplicationStatusIs(ApplicationStatus.PENDING);
        assertThat(applications).isEmpty();
    }
}