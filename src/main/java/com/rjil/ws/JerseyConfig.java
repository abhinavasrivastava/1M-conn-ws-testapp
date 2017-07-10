package com.rjil.ws;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.rjil.ws.resource.CinemaEventSocketMediator;
import com.rjil.ws.resource.MovieEvent;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        registerClasses(MovieEvent.class);
        registerClasses(CinemaEventSocketMediator.class);
    }
}