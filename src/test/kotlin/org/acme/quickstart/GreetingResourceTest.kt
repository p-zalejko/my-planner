package org.acme.quickstart;

import com.gmail.pzalejko.myplanner.jwt.JwtGenerator
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.eclipse.microprofile.jwt.Claims
import java.util.HashMap



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
                .body(`is`("hello unknown"))
    }

    @Test
    fun testHelloEndpoint_protectedAsUnknown() {
        given()
                .`when`().get("/hello/protected")
                .then()
                .statusCode(401)
    }

    @Test
    fun testHelloEndpoint_withJwtToken() {
        val jwtGenerator = JwtGenerator()
        var claimsJson = "/JwtClaims.json"
        val timeClaims = HashMap<String, Long>()
        val token = jwtGenerator.generateTokenString(claimsJson, timeClaims)

        given()
                .header("Authorization", "Bearer $token")
                .`when`().get("/hello/protected")
                .then()
                .statusCode(200)
                .body(`is`("hello Pawel"))
    }

}
