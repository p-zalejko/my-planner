package com.gmail.pzalejko.myplanner.event

import io.quarkus.mongodb.ReactiveMongoCollection
import io.quarkus.mongodb.ReactiveMongoDatabase
import org.bson.Document
import java.util.concurrent.CompletionStage
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class EventService {

    @Inject
    lateinit var reactiveMongoDatabase: ReactiveMongoDatabase

    fun getAllEvents(): CompletionStage<List<String>> {
        return getEventCollection()
                .find()
                .map { i -> i.getString("name") }
                .toList()
                .run()
    }

    private fun getEventCollection(): ReactiveMongoCollection<Document> {
        return reactiveMongoDatabase.getCollection("events")
    }

    fun addNew(name: Event): CompletionStage<Void> {
        val document = Document()
                .append("name", name.name)
        return getEventCollection().insertOne(document)
    }
}