package net.challenge.client.features.commands.impl

import net.challenge.client.core.ClientCore
import net.challenge.client.features.commands.Command
import net.challenge.client.features.commands.CommandInfo
import net.minecraft.util.ChatComponentText

@CommandInfo(name = "toggleCosmetic", description = "Toggle an Cosmetic :)", syntax = "Use §a'ToggleC <Cosmetic>'§7, to toggle a Cosmetic.", aliases = ["tc", "toggleC"])
object CmdToggleCosmetic : Command() {

    override fun run(arguments: Array<String>) {
        if (arguments.isEmpty()) {
            sendSyntax()
            return
        }

        val cosmetic = ClientCore.cosmeticRegistry.getCosmetic(arguments[0]) ?: let {
            sendSyntax()
            return
        }

        cosmetic.toggle()

        val stateText = if (cosmetic.enabled) "§aenabled" else "§cdisabled"

        sendMessage("The Cosmetic §a'" + cosmetic.name+ "' §7is now $stateText§7.")
    }
}