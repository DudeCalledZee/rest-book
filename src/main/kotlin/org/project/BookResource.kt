package org.project

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import java.util.*

@Path("api/books")
@Produces(MediaType.APPLICATION_JSON)
class BookResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun books() =
        listOf(
            Book(
                id = 1,
                title = "The Hobbit",
                author = "<NAME>",
                genre = "Fantasy",
                yearOfPublication = 1937
            ),
            Book(
                id = 2,
                title = "The Lord of the Rings",
                author = "<NAME>",
                genre = "Fantasy",
                yearOfPublication = 1954
            ),
            Book(
                id = 3,
                title = "The Hitchhiker's Guide to the Galaxy",
                author = "<NAME>",
                genre = "Science Fiction",
                yearOfPublication = 1979
            ),
            Book(
                id = 4,
                title = "The Adventures of Huckleberry Finn",
                author = "<NAME>",
                genre = "Children's Books",
                yearOfPublication = 1988
            ),
        )

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    fun countAllBooks() = books().size.toString()

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getBookById(@PathParam("id") id: Int) = books().find { it.id == id }?.let { Optional.of(it) }
}