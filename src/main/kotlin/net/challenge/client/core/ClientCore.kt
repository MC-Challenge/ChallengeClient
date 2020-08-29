package net.challenge.client.core

import me.zero.alpine.bus.EventBus
import me.zero.alpine.bus.ExtendEventManager
import net.challenge.client.core.info.ClientInfo
import net.challenge.client.features.cosmetics.registry.CosmeticRegistry
import net.norisk.core.features.command.CommandRegistry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object ClientCore : IClientCore {

    val info: ClientInfo = ClientInfo()

    val logger: Logger = LogManager.getLogger(ClientCore.javaClass)

    val eventBus: EventBus = ExtendEventManager()

    val commandRegistry: CommandRegistry = CommandRegistry()

    val cosmeticRegistry: CosmeticRegistry = CosmeticRegistry()

    override fun onPreStart(): Boolean {
        logger.debug("PRE-Start")

        return true
    }

    override fun onPostStart() {
        logger.debug("Post-Start")

        logger.info(info.toString())
        cosmeticRegistry.load()
    }

    override fun onShutdown() {
        logger.info("Shutdown ...")
    }
}