package com.gmail.pzalejko.myplanner.event;

import io.vertx.axle.core.Vertx
import org.reactivestreams.Publisher
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path;
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/events")
class EventController {

    @Inject
    lateinit var vertx: Vertx

    @Inject
    lateinit var service: EventService

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun getAll(): Publisher<String> {
        val toPublisherBuilder = vertx.periodicStream(200).toPublisherBuilder()
        return toPublisherBuilder
                .map { "hello" }
                .buildRs()
    }
}