package com.gmail.pzalejko.myplanner.eventstore.client

import com.gmail.pzalejko.myplanner.eventstore.model.Event
import java.time.Instant
import java.util.concurrent.Flow

/**
 * Provides access to the events.
 */
interface EventStoreClient {

    // TODO: CQRS / event sourcing
    fun getEvents(from: Instant, to: Instant): Flow.Publisher<Event>
}