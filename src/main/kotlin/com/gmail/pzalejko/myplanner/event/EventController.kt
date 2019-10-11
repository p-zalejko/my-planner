package com.gmail.pzalejko.myplanner.event;

import com.mongodb.client.MongoClient
import io.vertx.axle.core.Vertx
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
    lateinit var vertx: Vertx

    @Inject
    lateinit var service: EventService

    @Inject
    lateinit var mongodb: MongoClient

    @GET
    fun getAll(): CompletionStage<List<String>> {
        return service.getAllEvents()
    }

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