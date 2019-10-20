package com.gmail.pzalejko.myplanner.event;

import java.util.concurrent.CompletionStage
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class EventController {

    @Inject
    lateinit var service: EventService

    @GET
    fun getAll(): CompletionStage<List<String>> {
        return service.getAllEvents()
    }

//    @GET
//    fun getAll(): String {
//        return "foo"
//    }

    @POST
    fun addNew(name: EventDto): CompletionStage<Response> {
        val event = Event(name = name.name)
        val addNew = service.addNew(event)
        return addNew
                .thenApply(Response::ok)
                .thenApply(Response.ResponseBuilder::build);
    }

    class EventDto {
        var name: String = ""
    }
}