package net.challenge.client.ui.screen

import net.challenge.client.ui.screen.container.WidgetContainer
import net.challenge.client.ui.widget.IWidget
import net.minecraft.client.gui.GuiScreen

/**
 * TODO Doc
 */
open class WidgetScreen : GuiScreen() {

    private val widgetContainer = WidgetContainer()

    override fun initGui() {

        super.initGui()
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        widgetContainer.render(mouseX, mouseY)

        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun handleMouseInput() {
        widgetContainer.handleMouseInput()

        super.handleMouseInput()
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (widgetContainer.mouseClick(mouseX, mouseY, mouseButton)) return

        super.mouseClicked(mouseX, mouseY, mouseButton)
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {
        widgetContainer.mouseRelease(mouseX, mouseY, state)

        super.mouseReleased(mouseX, mouseY, state)
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        widgetContainer.keyType(typedChar, keyCode)

        super.keyTyped(typedChar, keyCode)
    }

    fun addWidgets(vararg widget: IWidget) {
        widgetContainer.addWidgets(*widget)
    }
}