package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import javax.ws.rs.core.MediaType
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.process.runtime.Network;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.hamcrest.CoreMatchers.hasItems

@QuarkusTest
open class EventControllerTest {

    companion object {

        @JvmStatic
        private var MONGO: MongodExecutable? = null

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            val version = Version.Main.V4_0
            val port = 27018
            val config = MongodConfigBuilder()
                    .version(version)
                    .net(Net(port, Network.localhostIsIPv6()))
                    .cmdOptions(MongoCmdOptionsBuilder().useNoJournal(false).build())
                    .build()
            MONGO = MongodStarter.getDefaultInstance().prepare(config)
            MONGO!!.start()
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            MONGO!!.stop();
        }
    }

    @Test
    fun test_add() {
        given()
                .`when`().get("/events")
                .then()
                .statusCode(200)
                .body("$.size()", `is`(0))

        given()
                .body("{\"name\": \"foo\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .`when`()
                .post("/events")
                .then()
                .statusCode(200)

        given()
                .`when`().get("/events")
                .then()
                .statusCode(200)
                .body("", hasItems("foo"));

    }

}
