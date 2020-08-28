package net.norisk.core.features.command

import net.challenge.client.features.commands.AbstractCommand
import java.util.*

class CommandRegistry {
    //are the client-commands
    private var clientCommands: Collection<AbstractCommand> = ArrayList()

    /**
     * **Adding the commands**
     *
     * This method, init's the command-registry
     */

    fun init() {
        registerCommands(

        )
    }

    /**
     * **Registering more commands**
     *
     * This method, registers more commands
     * @param commands are the commands, that will be registered
     */
    private fun registerCommands(vararg commands: AbstractCommand) {
        //registers the modules
        commands.forEach(this::registerCommand)
    }

    /**
     * **Registering one command**
     *
     * This method, registers one module
     * @param command is the command, that will be registered
     */

    private fun registerCommand(command: AbstractCommand) {
        clientCommands += command
    }

    /**
     * **Running the command**
     *
     * This method, searches after an command, and when it founds an command, it runs it!
     */

    fun runCommand(message: String): Boolean {
        val commandName = message.substring(1)
        val arguments = commandName.split(" ").toTypedArray()

        if (arguments.isEmpty()) return false

        if (!message.startsWith(".")) return false

        val command: AbstractCommand = getCommand(arguments[0])

        return run {
            val args = arrayOfNulls<String>(arguments.size - 1)
            System.arraycopy(arguments, 1, args, 0, arguments.size - 1)
            command.run(args)
            true
        }
    }

    /**
     * **Getting an command by name**
     * @return the command that was found, or null
     */

    fun getCommand(name: String): AbstractCommand {
        return clientCommands.stream().filter { mod: AbstractCommand -> name.equals(mod.name, ignoreCase = true) || mod.aliases.contains(name) }.findFirst().orElse(null)
    }

}