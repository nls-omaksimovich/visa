package pl.oleggye.visa.appointment.infrastracture.config;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.oleggye.visa.appointment.application.port.in.ElectApplicationsCommand;
import pl.oleggye.visa.appointment.application.port.in.ElectApplicationsUseCase;

@Configuration
@EnableScheduling
@AllArgsConstructor
@Log
public class ScheduledTasksConfig {

    private final ElectApplicationsUseCase electApplicationsUseCase;

    // Schedule this method to run every Friday at a specific time, e.g., at 6 PM UTC
//    @Scheduled(cron = "0 0 18 * * FRI", zone = "UTC")
    @Scheduled(cron = "0 */5 * * * *", zone = "UTC")
    public void executeRandomSelection() {
        log.info("Running elect application job");
        electApplicationsUseCase.electApplications(new ElectApplicationsCommand());
    }
}
