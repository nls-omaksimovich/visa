package pl.oleggye.visa.appointment.infrastracture.out.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.infrastracture.out.persistance.entity.VisaApplicationEntity;

import java.util.List;

public interface VisaApplicationRepository extends JpaRepository<VisaApplicationEntity, Long> {

    @Transactional(readOnly = true)
    List<VisaApplicationEntity> findByApplicationStatus(
            @Param("applicationStatus") ApplicationStatus applicationStatus
    );
}
