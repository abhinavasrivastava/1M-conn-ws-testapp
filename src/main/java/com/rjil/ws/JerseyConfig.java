package com.rjil.ws;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.rjil.ws.resource.WSSocketMediator;
import com.rjil.ws.resource.WSEvent;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        registerClasses(WSEvent.class);
        registerClasses(WSSocketMediator.class);
    }
}