package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

@QuarkusTest
open class GreetingResourceTest {

    companion object {

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            MongoDbConfig.start()
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            MongoDbConfig.stop()
        }
    }

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/hello")
          .then()
             .statusCode(200)
             .body(`is`("hello from Kotlin"))
    }

}
