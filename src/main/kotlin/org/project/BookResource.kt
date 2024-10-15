package org.project

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.logging.Logger

@Path("api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BookResource {

    @Inject
    lateinit var bookRepository: BookRepository

    @Inject
    lateinit var logger: Logger

    @GET
    fun books() = bookRepository.getAllBooks()

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    fun countAllBooks() = bookRepository.getAllBooks().size.toString()

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getBookById(@PathParam("id") id: Int) =
        bookRepository.getBook(id)
            .also { logger.info("a log injected, the Book is: $it") }

    @POST
    fun addBook(book: Book): Book {
        val allBooks = bookRepository.getAllBooks()
        allBooks.add(book)
            .let { if (allBooks.size > bookRepository.getMaxSize().toInt()) logger.warn("We have too many books") }
        return book
    }
}
