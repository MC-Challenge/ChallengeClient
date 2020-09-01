package net.challenge.client.features.modules

import net.challenge.client.ui.font.FontHandler
import net.challenge.client.value.VTag
import net.challenge.client.value.list.BooleanValue
import java.awt.Color

/**
 * # SimpleHudModule
 *
 * TODO Doc
 */
abstract class SimpleHudModule : HudModule() {

    /**
     * TODO Doc
     */
    @VTag(name = "Shadow", description = "Should the text have a shadow")
    protected val hasShadow: BooleanValue = BooleanValue( false)


    /**
     * Get the value of the module
     *
     * @return The Value of the module.
     */
    protected abstract fun getValue(): String

    /**
     * Get the Display-Name of the module
     *
     * @return The Display-Name of the module
     */
    protected abstract fun getDisplayName(): String


    override fun drawElement(mouseX: Int, mouseY: Int, partialTicks: Float) {
        val font = FontHandler.mcFontRenderer
        val text = getDisplayName() + ": " + getValue()

        font.drawString(text, position.getAbsoluteX(), position.getAbsoluteY(), Color.WHITE.rgb)
    }

    override fun getElementWidth(): Int {
        return FontHandler.mcFontRenderer.getStringWidth("${ getDisplayName() }: ${ getValue() }")
    }

    override fun getElementHeight(): Int {
        return FontHandler.mcFontRenderer.getFontHeight()
    }
}