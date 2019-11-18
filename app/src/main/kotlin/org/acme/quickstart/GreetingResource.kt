package org.acme.quickstart;


import com.gmail.pzalejko.myplanner.calendar.Event
import com.gmail.pzalejko.myplanner.calendar.client.google.GoogleCalendarClient
import javax.annotation.security.PermitAll
import javax.enterprise.context.RequestScoped
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext
import org.eclipse.microprofile.jwt.JsonWebToken
import javax.annotation.security.RolesAllowed
import javax.inject.Inject


@Path("/hello")
@RequestScoped
open class GreetingResource {

    @Inject
    lateinit var jwt: JsonWebToken

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    open fun hello(@Context ctx: SecurityContext): String {
        val callerName = ctx.userPrincipal?.name ?: "unknown"
        return "hello $callerName"
    }

    @GET
    @Path("/protected")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed(value = ["Echoer", "Subscriber"])
    open fun helloProtected(@Context ctx: SecurityContext): String {
        val callerName = ctx.userPrincipal?.name ?: "unknown"
        return "hello $callerName"
    }

    @GET
    @Path("/my-events")
    @Produces(MediaType.TEXT_PLAIN)
    open fun getEvents(@Context ctx: SecurityContext): List<Event> {
        val `in` = GreetingResource::class.java.getResourceAsStream("/credentials.json")

        val client = GoogleCalendarClient(`in`)
        return client.getEvents()
    }
}