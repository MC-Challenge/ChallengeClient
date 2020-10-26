package net.challenge.client.ui.widget.elements.renderer.settings

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.font.fancy.GLFont
import net.challenge.client.ui.widget.elements.settings.CategoryButton
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.challenge.client.ui.widget.utils.RenderUtils
import java.awt.Color

class CategoryButtonRenderer : IWidgetRenderer<CategoryButton> {

    // TODO: get this piece of garbage in an separate manager/handler
    private var standardFont: GLFont = FontHandler.getFancyFontRenderer("raleway/raleway-medium", 16)
    private var iconFont: GLFont = FontHandler.getFancyFontRenderer("ClickGUI", 40)

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

        val textX = x + 10 + iconFont.getWidth(char)
        iconFont.drawString(char, x.toDouble() + 4, y + widget.height / 2 - iconFont.height.toDouble() / 2, textColor)
        standardFont.drawStringWithShadow(widget.category.publicName, textX.toDouble(), y + widget.height / 2 - standardFont.height.toDouble() / 2, textColor)
    }
}