package net.challenge.client.ui.screen

import net.challenge.client.ui.widget.elements.button.Button
import net.minecraft.client.gui.Gui
import java.awt.Color

/**
 * Screen to test the widgets
 */
class TestWidgetScreen : WidgetScreen() {

    init {
        val btn = Button("Test Button")
        btn.position.setAbsolute(100, 100)
        btn.width = 100
        btn.height = 40

        addWidgets(btn)
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        Gui.drawRect(0, 0, width, height, Color.BLACK.rgb)

        super.drawScreen(mouseX, mouseY, partialTicks)
    }
}