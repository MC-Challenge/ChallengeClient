package net.challenge.client.features.commands.impl

import net.challenge.client.core.ClientCore
import net.challenge.client.features.commands.Command
import net.challenge.client.features.commands.CommandInfo
import net.challenge.client.features.commands.ICommand

@CommandInfo(name = "help", description = "Help the player to use the commands", syntax = "Use §a'help '§7.", aliases = [ "h" ])
object CmdHelp : Command() {

    override fun run(arguments: Array<String>) {
        sendMessage("--- Help ---")
        ClientCore.commandHandler.commands.forEach(ICommand::sendSyntax)
    }
}