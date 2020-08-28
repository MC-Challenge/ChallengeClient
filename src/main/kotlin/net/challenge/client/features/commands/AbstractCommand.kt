package net.challenge.client.features.commands

import com.google.common.collect.Lists
import net.challenge.client.core.ClientCore
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.norisk.core.features.command.annotations.CommandInfo

abstract class AbstractCommand {
    var name: String = ""
    var description: String = ""
    var syntax: String = ""
    var aliases: Collection<String?> = Lists.newArrayList()


    /**
     * **Init**
     *
     * This method init's the command info's, with the annotation
     */
    init {
        val info: CommandInfo = javaClass.getAnnotation(CommandInfo::class.java)
        name = info.name
        description = info.description
        syntax = info.syntax
        aliases = info.aliases.split(", ")
    }

    /**
     * **Running the command**
     *
     * This method, will run, when the player typed in the command
     */
    abstract fun run(arguments: Array<String?>?)


    /**
     * **Sending the Syntax to the player**
     *
     * this method, sends the syntax, of this command, to the player
     */
    open fun sendSyntax() {
        if (syntax != "") sendMessage(syntax)
    }

    /**
     * **Sending messages to the player**
     *
     * This method, sends an message, to the player
     */
    open fun sendMessage(msg: String) {
        if (syntax != "") Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText("§a" + ClientCore.info.name + " §8> §7" + msg))
    }

}