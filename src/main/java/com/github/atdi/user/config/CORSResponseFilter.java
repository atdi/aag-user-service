package com.github.atdi.user.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

/**
 * Created by aurelavramescu on 18/08/15.
 */
public class CORSResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        headers.add("Access-Control-Allow-Headers", "Access-Control-Allow-Origin, " +
                "Content-Type, " +
                "X-Codingpedia, " +
                "Location");
        headers.add("Access-Control-Expose-Headers","Location");
    }
}
