package pl.oleggye.visa.appointment.infrastracture.out.persistance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;
import pl.oleggye.visa.appointment.infrastracture.out.persistance.entity.VisaApplicationEntity;

@Mapper
public interface VisaApplicationMapper {
    VisaApplicationMapper INSTANCE = Mappers.getMapper(VisaApplicationMapper.class);

    VisaApplication visaApplicationEntityToVisaApplication(VisaApplicationEntity visaApplicationEntity);

    VisaApplicationEntity visaApplicationToVisaApplicationEntity(VisaApplication visaApplication);
}
