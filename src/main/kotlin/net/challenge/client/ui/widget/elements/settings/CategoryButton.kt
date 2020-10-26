package net.challenge.client.ui.widget.elements.settings

import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.ui.widget.ClickableWidget
import java.awt.Color

class CategoryButton(val category: ModuleCategory) : ClickableWidget<CategoryButton>() {

    var color: Color = Color(50, 50, 50)

    var centered = true

    fun setColor(color: Color): CategoryButton {
        this.color = color
        return this
    }

    fun setCentered(centered: Boolean): CategoryButton {
        this.centered = centered
        return this
    }

}