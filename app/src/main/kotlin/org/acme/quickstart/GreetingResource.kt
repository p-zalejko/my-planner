package org.acme.quickstart;

import org.eclipse.microprofile.opentracing.Traced
import javax.annotation.security.PermitAll
import javax.annotation.security.RolesAllowed
import javax.enterprise.context.RequestScoped
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext


@Path("/hello")
@RequestScoped
@Traced
open class GreetingResource {

//    @Inject
//    lateinit var jwt: JsonWebToken

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
}