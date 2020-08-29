package net.challenge.client.ui.events.input

/**
 * TODO Doc
 */
interface IGuiKeyType {

    /**
     * It is executed as soon as a key is pressed
     *
     * @param typedChar
     * @param keyCode
     */
    fun keyType(typedChar: Char, keyCode: Int)
}