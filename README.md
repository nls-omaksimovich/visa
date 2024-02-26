```
com.yourcompany.visaagency
├── application
│   ├── port
│   │   ├── in
│   │   │   ├── ApplyForVisaUseCase.java
│   │   │   ├── CheckRegistrationUseCase.java
│   │   │   ├── ApplyForVisaUseCase.java
│   │   ├── out
│   │       ├── VisaApplicationPort.java
│   ├── service
│       ├── ApplyForVisaService.java
│       ├── CheckRegistrationService.java
├── domain
│   ├── model
│   │   ├── VisaApplication.java
│   ├── exception
│       ├── VisaApplicationException.java
├── infrastructure
├── config
│   ├── ScheduledTasksConfig.java
├── persistence
│   ├── entity
│   │   ├── VisaApplicationEntity.java
│   ├── repository
│       ├── VisaApplicationRepository.java
├── web
├── controller
│   ├── VisaApplicationController.java
├── dto
├── VisaApplicationRequest.java
├── VisaApplicationStatusResponse.java
```

Key Components
## Domain Model (VisaApplication.java):
Represents the visa application with attributes like applicant details, requested date, timeslot, and status.
## Ports 
(ApplyForVisaUseCase.java, CheckRegistrationUseCase.java, VisaApplicationPort.java): Define the primary ways in and out of the application (application services and repository interfaces).
## Services
(ApplyForVisaService.java, CheckRegistrationService.java): Implements the use cases interfaces, containing business logic.
## Entities
(VisaApplicationEntity.java): Database entities that map the domain models to the database.
## Repositories
(VisaApplicationRepository.java): Interfaces for database access, typically extending JpaRepository.
## Controller
(VisaApplicationController.java): Rest controllers to handle HTTP requests.
## DTOs
(VisaApplicationRequest.java, VisaApplicationResponse.java): Data Transfer Objects for web request and response.
## Config
(ScheduledTasksConfig.java): Configuration for scheduled tasks, like the random selection process.
