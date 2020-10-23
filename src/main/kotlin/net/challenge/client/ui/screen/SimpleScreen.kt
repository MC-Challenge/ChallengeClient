package net.challenge.client.ui.screen

import net.challenge.client.ui.screen.container.WidgetContainer
import net.challenge.client.ui.widget.Widget
import net.minecraft.client.gui.GuiScreen

class SimpleScreen(lastScreen: GuiScreen? = null) : WidgetScreen(lastScreen) {
    init {
        addWidgets(

        )
    }
}

class SimpleContainer() : WidgetContainer() {


    override fun render(mouseX: Int, mouseY: Int) {

    }
}