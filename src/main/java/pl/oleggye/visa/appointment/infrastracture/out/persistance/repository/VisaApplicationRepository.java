package pl.oleggye.visa.appointment.infrastracture.out.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.infrastracture.out.persistance.entity.VisaApplicationEntity;

import java.util.List;

public interface VisaApplicationRepository extends JpaRepository<VisaApplicationEntity, Long> {

    @Query("SELECT vae FROM VisaApplicationEntity vae WHERE vae.applicationStatus = :applicationStatus")
    @Transactional(readOnly = true)
    List<VisaApplicationEntity> findAllWhereApplicationStatusIs(
            @Param("applicationStatus") ApplicationStatus applicationStatus
    );
}
