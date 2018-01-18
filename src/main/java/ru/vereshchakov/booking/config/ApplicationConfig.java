package ru.vereshchakov.booking.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vereshchakov.booking.repository.PersonRepo;
import ru.vereshchakov.booking.repository.ScheduleRepo;
import ru.vereshchakov.booking.service.ScheduleService;
import ru.vereshchakov.booking.service.ScheduleServiceImpl;

/**
 * Created by dvere on 07.01.2018.
 */

@Configuration
public class ApplicationConfig {

    @Bean
    public ScheduleService personCalendar(@Qualifier("personScheduleRepoMock") ScheduleRepo scheduleRepo,
                                          @Qualifier("personRepoMock")PersonRepo personRepo) {
        return new ScheduleServiceImpl(scheduleRepo, personRepo);
    }

}
