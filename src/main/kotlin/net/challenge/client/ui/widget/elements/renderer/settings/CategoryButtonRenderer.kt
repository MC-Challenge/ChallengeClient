package net.challenge.client.ui.widget.elements.renderer.settings

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.font.fancy.GLFont
import net.challenge.client.ui.widget.elements.settings.CategoryButton
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.challenge.client.ui.widget.utils.RenderUtils
import org.lwjgl.opengl.GL11
import java.awt.Color

class CategoryButtonRenderer : IWidgetRenderer<CategoryButton> {

    override fun render(widget: CategoryButton, mouseX: Int, mouseY: Int) {
        val x = widget.position.getAbsoluteX()
        val y = widget.position.getAbsoluteY()

        val hover = widget.isHover(mouseX, mouseY)

        var bgColor = widget.color
        var textColor = -1

        if (hover) {
            bgColor = Color(35, 34, 35)
        }

        if (widget.category == ClientCore.customHud.settingScreen!!.currentCategory) {
            bgColor = Color(25, 24, 25)
            textColor = ClientCore.customHud.settingScreen!!.mainColor.rgb
        }

        RenderUtils.drawRect(x.toFloat(), y.toFloat(), widget.width.toFloat(), widget.height.toFloat(), bgColor)

        if (widget.category == ClientCore.customHud.settingScreen!!.currentCategory) {
            RenderUtils.drawRect(x.toFloat(), y.toFloat(), 1F, widget.height.toFloat(), ClientCore.customHud.settingScreen!!.mainColor.rgb)
        }

        val char = widget.category.character
        var iconFont = widget.font

        ClientCore.customHud.settingScreen?.let {
            iconFont = it.iconFont
        }

        val textX = x + 10 + iconFont.getWidth(char)

        GL11.glPushMatrix()
        GL11.glEnable(GL11.GL_BLEND)

        iconFont.drawString(char, x.toDouble() + 4, y + widget.height / 2 - iconFont.height.toDouble() / 2, textColor)
        widget.font.drawStringWithShadow(widget.category.publicName, textX.toDouble(), y + widget.height / 2 - widget.font.height.toDouble() / 2, textColor)

        GL11.glDisable(GL11.GL_BLEND)
        GL11.glPopMatrix()
    }
}