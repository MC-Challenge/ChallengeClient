package net.challenge.client.features.commands.impl

import net.challenge.client.core.ClientCore
import net.challenge.client.features.commands.Command
import net.challenge.client.features.commands.CommandInfo
import net.minecraft.util.ChatComponentText

@CommandInfo(name = "toggle", description = "Toggle an Module :)", syntax = "Use §a'Toggle <Module>'§7, to toggle a Module.", aliases = [ "t" ])
object CmdToggle : Command() {

    override fun run(arguments: Array<String>) {
        if (arguments.isEmpty()) {
            sendSyntax()
            return
        }

        val module = ClientCore.moduleRegistry.getModule(arguments[0]) ?: let {
            sendSyntax()
            return
        }

        module.toggle()

        val stateText = if (module.isEnabled()) "§aenabled" else "§cdisabled"

        sendMessage("The Module is now $stateText§7.")
    }
}