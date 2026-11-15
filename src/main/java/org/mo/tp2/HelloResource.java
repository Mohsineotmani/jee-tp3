package org.mo.tp2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World2!";
        /*return Response.ok("Hello, World!")
                .header("Access-Control-Allow-Origin", "http://localhost:5173")
                .build();*/
    }
}