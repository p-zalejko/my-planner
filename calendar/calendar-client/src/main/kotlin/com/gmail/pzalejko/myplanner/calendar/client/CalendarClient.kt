package com.gmail.pzalejko.myplanner.calendar.client

import com.gmail.pzalejko.myplanner.calendar.Event
import java.time.Instant
import java.util.concurrent.Flow

/**
 * Provides access to the events stored in calendar-oriented manner.
 */
interface CalendarClient {

    fun getEvents(from: Instant, to: Instant): Flow.Publisher<Event>
}