package net.challenge.client.ui.widget.elements.list

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.font.fancy.GLFont
import net.challenge.client.ui.position.IPosition
import net.challenge.client.ui.position.Position
import net.challenge.client.ui.position.ScaledPosition
import net.challenge.client.ui.screen.container.WidgetContainer
import net.challenge.client.ui.widget.IGuiWidget
import net.challenge.client.ui.widget.utils.Spacing

class WidgetList : WidgetContainer(), IItemList<WidgetList> {

    override var position: IPosition = Position(0.0, 0.0)

    override var width: Int = 0

    override var height: Int = 0

    override var visible: Boolean = true

    override val padding: Spacing = Spacing()

    override var disable: Boolean = false

    override var backgroundColor: Int = java.awt.Color(0, 0, 0).rgb

    override fun render(mouseX: Int, mouseY: Int) {
        disable = !this.isHover(mouseX, mouseY)
        updateWidgetsCache()

        super<IItemList>.render(mouseX, mouseY)
        val standardFont = ClientCore.customHud.settingScreen!!.standardFont

        if (widgets.isEmpty()) {
            val text = "No Items."
            val textWidth = standardFont.getWidth(text)
            standardFont.drawStringWithShadow(text, position.getAbsoluteX().toDouble() + (width / 2 - textWidth/2), position.getAbsoluteY().toDouble() + (height / 2 - standardFont.height / 2), -1)
        }
    }

    override fun renderItem(index: Int, x: Float, y: Float, width: Float, mouseX: Int, mouseY: Int): Float {
        val widget = visibleWidgets[index]

        widget.position.setAbsolute(x.toInt(), y.toInt())
        widget.setWidth(width.toInt())
        widget.render(mouseX, mouseY)

        return widget.height.toFloat()
    }

    override fun getListLength(): Int {
        return visibleWidgets.size
    }

    /**
     * Register widgets
     *
     * @param widget This widget will be added to the [widgets] list
     *
     * @return this
     */
    fun widgets(vararg widget: IGuiWidget<*>): WidgetList {
        super.addWidgets(*widget)

        return this
    }

}