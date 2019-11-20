package com.gmail.pzalejko.myplanner.calendar

import java.time.Instant

/**
 * An event, appointment etc.
 */
interface Event {

    fun getId(): String

    fun getStartDate(): Instant

    fun getEndDate(): Instant

    fun getTitle(): String

    fun getDescription(): String
}