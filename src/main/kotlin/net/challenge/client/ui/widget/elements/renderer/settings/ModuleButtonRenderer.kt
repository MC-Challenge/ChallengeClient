package net.challenge.client.ui.widget.elements.renderer.settings

import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.font.fancy.GLFont
import net.challenge.client.ui.widget.elements.settings.ModuleButton
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.challenge.client.ui.widget.utils.RenderUtils
import org.lwjgl.opengl.GL11
import java.awt.Color

class ModuleButtonRenderer : IWidgetRenderer<ModuleButton> {

    override fun render(widget: ModuleButton, mouseX: Int, mouseY: Int) {
        val x = widget.position.getX()
        val y = widget.position.getY()

        val hover = widget.isHover(mouseX, mouseY)

        var bgColor = widget.color
        var textColor = Color(200, 200, 200).rgb

        if (hover) {
            bgColor = Color(35, 34, 35)
        }

        if(widget.module.isEnabled())
             textColor = -1


        var textX = x + widget.width / 2 - widget.font.getWidth(widget.module.name) / 2
        if (!widget.centered) textX = x + 2

        RenderUtils.drawRect(x.toFloat(), y.toFloat(), widget.width.toFloat(), widget.height.toFloat(), bgColor)

        GL11.glEnable(GL11.GL_BLEND)
        widget.font.drawStringWithShadow(widget.module.name, textX.toDouble(), y + widget.height / 2 - widget.font.height.toDouble() / 2, textColor)
        GL11.glDisable(GL11.GL_BLEND)

        for (i in 0..2) {
            RenderUtils.drawCircle(x.toDouble() + (widget.width - 6 - i*3), y.toDouble() + widget.height/2.0, 0.5F, textColor)
        }
    }
}