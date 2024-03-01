package pl.oleggye.visa.appointment.infrastracture.out.persistance.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.infrastracture.out.persistance.entity.VisaApplicationEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db?TC_REUSABLE=true",
        "spring.test.database.replace=none"
})
@Transactional(propagation = Propagation.NOT_SUPPORTED) // if someone decided to use CUD operations in the test
class VisaApplicationRepositoryTest {
    @Autowired
    private VisaApplicationRepository visaApplicationRepository;

    @Test
    void findAllWhereApplicationStatusIs() {
        List<VisaApplicationEntity> applications = visaApplicationRepository.findByApplicationStatus(ApplicationStatus.PENDING);
        assertThat(applications).isEmpty();
    }
}
