package com.gmail.pzalejko.myplanner;

import javax.ws.rs.GET
import javax.ws.rs.Path;
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/events")
class EventController{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll() = "foo"

}