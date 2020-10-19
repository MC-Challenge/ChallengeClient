package net.challenge.client.features.modules

import net.challenge.client.ui.font.FontHandler
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool
import net.challenge.configu.value.impl.VColor
import java.awt.Color

/**
 * # SimpleHudModule
 *
 * TODO Doc
 */
abstract class SimpleHudModule : HudModule() {

    /**
     * Option, if the text should drop an shadow
     */
    @VTag(name = "Shadow", description = "Should the text have a shadow")
    protected val hasShadow = VBool(false)


    /**
     * Option, if the text should have a prefix
     */
    @VTag(name = "Prefix", description = "Should have a Prefix")
    protected val hasPrefix = VBool(false)

    /**
     * Color Red
     */
    @VTag(name = "Color", description = "Color")
    protected val color = VColor(Color.BLUE)


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
        var text = getDisplayName() + ": " + getValue()

        if (!hasPrefix.value)
            text = getValue()

        if (hasShadow.value) font.drawStringWithShadow(text, position.getAbsoluteX().toFloat(), position.getAbsoluteY().toFloat(), color.value.rgb)
        else font.drawString(text, position.getAbsoluteX(), position.getAbsoluteY(), color.value.rgb)
    }

    override fun getElementWidth(): Int {
        var text = getDisplayName() + ": " + getValue()

        if (!hasPrefix.value)
            text = getValue()
        return FontHandler.mcFontRenderer.getStringWidth(text)
    }

    override fun getElementHeight(): Int {
        return FontHandler.mcFontRenderer.getFontHeight()
    }
}