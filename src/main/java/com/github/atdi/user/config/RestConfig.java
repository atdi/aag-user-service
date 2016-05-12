package com.github.atdi.user.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Created by aurelavramescu on 11/05/16.
 */
@Component
@ApplicationPath("/api")
public class RestConfig extends ResourceConfig {

    public RestConfig() {
        register(CORSResponseFilter.class);
        packages("com.github.atdi.user.resources");
    }
}
