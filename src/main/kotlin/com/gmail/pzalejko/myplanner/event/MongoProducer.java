package com.gmail.pzalejko.myplanner.event;

import io.quarkus.mongodb.ReactiveMongoClient;
import io.quarkus.mongodb.ReactiveMongoDatabase;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MongoProducer {

    @Produces
    public ReactiveMongoDatabase produceAssetDatabase(ReactiveMongoClient mongoClient) {
        return mongoClient.getDatabase("my-planner");
    }
}