package com.gmail.pzalejko.myplanner.calendar.client

import com.gmail.pzalejko.myplanner.calendar.Event

interface CalendarClient {

    fun getEvents(): List<Event>
}