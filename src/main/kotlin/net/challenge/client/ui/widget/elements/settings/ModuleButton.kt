package net.challenge.client.ui.widget.elements.settings

import net.challenge.client.features.modules.IModule
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.font.fancy.GLFont
import net.challenge.client.ui.widget.ClickableWidget
import net.challenge.client.ui.widget.ITextWidget
import java.awt.Color

class ModuleButton(val module: IModule) : ClickableWidget<ModuleButton>(), ITextWidget<ModuleButton> {

    override var font: GLFont = FontHandler.getFancyFontRenderer("Raleway-Medium", 16)

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