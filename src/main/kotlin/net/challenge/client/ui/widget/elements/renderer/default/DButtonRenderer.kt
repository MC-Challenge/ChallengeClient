package net.challenge.client.ui.widget.elements.renderer.default

import net.challenge.client.ui.widget.elements.button.Button
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import java.awt.Color

/**
 * TODO Doc
 */
class DButtonRenderer : IWidgetRenderer<Button> {

    override fun render(widget: Button, mouseX: Int, mouseY: Int) {
        val x = widget.position.getAbsoluteX()
        val y = widget.position.getAbsoluteY()

        Gui.drawRect(x, y, x + widget.width, y + widget.height, Color.WHITE.rgb)
        Minecraft.getMinecraft().fontRendererObj.drawString(widget.name, x, y, Color.RED.rgb)
    }
}