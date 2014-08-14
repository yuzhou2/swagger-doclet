package com.carma.swagger.doclet.sample.resources;

import com.carma.swagger.doclet.sample.api.Recursive;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/Recursive")
public class RecursiveResource {
    @POST
    public Recursive recurse(Recursive recursive) {
        return recursive;
    }
}
