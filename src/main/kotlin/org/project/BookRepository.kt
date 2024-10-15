package org.project

import jakarta.enterprise.context.ApplicationScoped
import java.util.Optional

@ApplicationScoped
class BookRepository {

    fun getAllBooks() = listOf(
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

    fun getBook(id: Int) = getAllBooks().find { it.id == id }?.let { Optional.of(it) }
}
