package ru.vereshchakov.booking.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by dvere on 07.01.2018.
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("ru.vereshchakov.booking.controller");
    }
}
