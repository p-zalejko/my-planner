package com.gmail.pzalejko.myplanner.eventstore.model

interface EventStore {

    fun save(event: Event): Event
}