package net.challenge.client.core

import net.challenge.client.core.info.ClientInfo
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object ClientCore : IClientCore {

    val info: ClientInfo = ClientInfo()

    val logger: Logger = LogManager.getLogger(ClientCore.javaClass)


    override fun onPreStart(): Boolean {
        logger.debug("PRE-Start")

        return true
    }

    override fun onPostStart() {
        logger.debug("Post-Start")

        logger.info(info.toString())
    }

    override fun onShutdown() {
        logger.info("Shutdown ...")
    }
}