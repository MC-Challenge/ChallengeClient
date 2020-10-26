package net.challenge.client.features.commands.handler

import net.challenge.client.features.commands.ICommand


interface ICommandHandler {

    /**
     * Collection of all registered commands.
     */
    val commands: Collection<ICommand>

    /**
     * This method, searches after an command, and when it founds an command, it runs it.
     *
     * @param message Input text.
     *
     * @return TODO Doc
     */
    fun onInput(message: String): Boolean
}