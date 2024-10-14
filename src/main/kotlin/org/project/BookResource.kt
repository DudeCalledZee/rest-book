package org.project

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("api/books")
class BookResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello Just another day another application"
}