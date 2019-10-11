package com.gmail.pzalejko.myplanner.event;

import com.mongodb.client.MongoClient
import io.vertx.axle.core.Vertx
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

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
    fun addNew(name: EventDto): CompletionStage<String> {
        val event = Event(name = name.name)
        val addNew = service.addNew(event)
        return addNew.thenCompose { d -> CompletableFuture.completedFuture("OK") }
    }

    class EventDto {
        var name: String = ""
    }
}