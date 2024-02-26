package pl.oleggye.visa.appointment.infrastracture.in.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.oleggye.visa.appointment.domain.model.ApplicationStatus;
import pl.oleggye.visa.appointment.domain.model.VisaApplication;
import pl.oleggye.visa.appointment.infrastracture.in.web.dto.VisaApplicationRequest;
import pl.oleggye.visa.appointment.infrastracture.in.web.dto.VisaApplicationResponse;

@Mapper
public interface VisaApplicationMapper {

    VisaApplicationMapper INSTANCE = Mappers.getMapper(VisaApplicationMapper.class);

    @Mapping(target = "id", ignore = true)
    VisaApplication visaApplicationRequestToVisaApplication(
            VisaApplicationRequest visaApplicationRequest,
            ApplicationStatus applicationStatus
    );

    VisaApplicationResponse visaApplicationToVisaApplicationResponse(VisaApplication visaApplication);
}
