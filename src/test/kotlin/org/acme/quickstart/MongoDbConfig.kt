package org.acme.quickstart;

import org.junit.jupiter.api.AfterAll
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.process.runtime.Network;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;

open class MongoDbConfig {

    companion object {

        @JvmStatic
        private var MONGO: MongodExecutable? = null

        @JvmStatic
        fun start() {
            val version = Version.Main.V4_0
            val port = 27019
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
        fun stop() {
            MONGO!!.stop();
        }
    }

}
