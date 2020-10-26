package net.challenge.client.features.commands

/**
 * A command that can be executed.
 */
interface ICommand {

    /**
     * Running the command.
     *
     * @param arguments Typed arguments.
     */
    fun run(arguments: Array<String>)

    /**
     * Check valid name.
     *
     * @param name Name to check.
     */
    fun isValidName(name: String): Boolean

    /**
     * Send the syntax of the command
     */
    fun sendSyntax()

    /**
     * Send message to the player.
     *
     * @param message Message to send.
     */
    fun sendMessage(message: String)
}