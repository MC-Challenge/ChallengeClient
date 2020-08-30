package net.challenge.client.ui.widget.elements.renderer.default

import net.challenge.client.ui.widget.elements.button.Button
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.minecraft.client.Minecraft
import java.awt.Color

/**
 * TODO Doc
 */
class DButtonRenderer : IWidgetRenderer<Button> {

    override fun render(btn: Button, mouseX: Int, mouseY: Int) {
        val x = btn.position.getAbsoluteX()
        val y = btn.position.getAbsoluteY()

        Minecraft.getMinecraft().fontRendererObj.drawString(btn.name, x, y, Color.WHITE.rgb)
    }
}