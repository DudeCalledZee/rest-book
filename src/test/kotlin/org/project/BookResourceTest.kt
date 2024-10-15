package org.project

import com.google.common.net.HttpHeaders
import groovy.json.JsonParser
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import jakarta.ws.rs.core.MediaType
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class BookResourceTest {

    @Test
    fun `should GET count`() {
        given()
            .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN)
            .`when`().get("api/books/count")
            .then()
            .statusCode(200)
            .body(`is`("4"))
    }

    @Test
    fun `should GET book by Id`() {
        given()
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
            .pathParams("id", 1)
            .`when`().get("api/books/{id}")
            .then()
            .statusCode(200)
            .body("title", `is`("The Hobbit"))
            .body("author", `is`("<NAME>"))
            .body("genre", `is`("Fantasy"))
            .body("yearOfPublication", `is`(1937))
            .body("id", `is`(1))
    }

    @Test
    fun `should GET all books`() {
        given()
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
            .`when`().get("api/books")
            .then()
            .statusCode(200)
            .body("size()", `is`(4))
    }

    @Test
    fun `should POST book`() {
        val expected =
            """
            {
            "id" : 6,
            "yearOfPublication" : 1942,
            "title": "The Great Gatsby",
            "author": "F. Scott Fitzgerald"
            }
            """.trimIndent()

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(expected)
            .`when`().post("api/books")
            .then()
            .statusCode(200)
            .body("id", `is`(6))
            .body("yearOfPublication", `is`(1942))
            .body("title", `is`("The Great Gatsby"))
            .body("author", `is`("F. Scott Fitzgerald"))
    }


}
