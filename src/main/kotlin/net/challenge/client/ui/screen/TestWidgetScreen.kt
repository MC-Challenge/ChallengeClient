package net.challenge.client.ui.screen

import net.challenge.client.ui.widget.elements.button.Button

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
}