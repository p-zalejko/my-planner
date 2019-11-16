package com.gmail.pzalejko.myplanner.event.calendarclient.google

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.Event
import java.io.File
import java.io.InputStreamReader

class GoogleCalendarClient {

    fun getEvents(): MutableList<Event>? {
        val jacksonFactory = JacksonFactory.getDefaultInstance()

        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        val service = Calendar.Builder(HTTP_TRANSPORT, jacksonFactory, getCredentials())
                .setApplicationName("Google Calendar API Java Quickstart")
                .build()

        val now = DateTime(System.currentTimeMillis())
        val events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute()
        return events.items
    }

    fun getCredentials(): Credential? {
        val `in` = CalendarQuickstart::class.java.getResourceAsStream("/credentials.json")
        val jacksonFactory = JacksonFactory.getDefaultInstance()
        val clientSecrets = GoogleClientSecrets.load(jacksonFactory, InputStreamReader(`in`))

        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        listOf(CalendarScopes.CALENDAR_READONLY)
        val flow = GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, jacksonFactory, clientSecrets, listOf(CalendarScopes.CALENDAR_READONLY))
                .setDataStoreFactory(FileDataStoreFactory(File("tokens")))
                .setAccessType("offline")
                .build()
        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }
}