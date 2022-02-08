package org.sbereducation.rest.json;

import com.github.ksuid.KsuidGenerator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/id")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IdResource {
    @GET
    public Response getId() {
        return Response.ok(new Id(KsuidGenerator.generate())).build();
    }
}