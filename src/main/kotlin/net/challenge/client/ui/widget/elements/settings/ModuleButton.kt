package net.challenge.client.ui.widget.elements.settings

import net.challenge.client.features.modules.IModule
import net.challenge.client.ui.widget.ClickableWidget
import java.awt.Color

class ModuleButton(val module: IModule) : ClickableWidget<ModuleButton>() {

    var color: Color = Color(50, 50, 50)

    var centered = true

    fun setColor(color: Color): ModuleButton {
        this.color = color
        return this
    }

    fun setCentered(centered: Boolean): ModuleButton {
        this.centered = centered
        return this
    }

}