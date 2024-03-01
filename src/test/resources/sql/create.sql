create table IF NOT EXISTS visa_application
(
    id                  bigserial
        primary key,
    requested_date_time timestamp(6),
    applicant_name      varchar(255),
    application_status  varchar(255)
        constraint visa_application_application_status_check
            check ((application_status)::text = ANY
                   ((ARRAY ['SELECTED'::character varying, 'PENDING'::character varying, 'NON_SELECTED'::character varying])::text[])),
    timeslot            varchar(255)
);
