package net.challenge.client.ui.widget.elements.list

import net.challenge.client.ui.position.IPosition
import net.challenge.client.ui.position.ScaledPosition
import net.challenge.client.ui.screen.container.WidgetContainer
import net.challenge.client.ui.widget.IGuiWidget
import net.challenge.client.ui.widget.utils.RenderUtils
import net.challenge.client.ui.widget.utils.Spacing
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11

class WidgetList : WidgetContainer(), IItemList<WidgetList> {

    override var position: IPosition = ScaledPosition(0, 0)

    override var width: Int = 0

    override var height: Int = 0

    override var visible: Boolean = true

    override val padding: Spacing = Spacing()

    override var disable: Boolean = false

    override fun render(mouseX: Int, mouseY: Int) {
        disable = !this.isHover(mouseX, mouseY)
        updateWidgetsCache()

        super<IItemList>.render(mouseX, mouseY)
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
    fun widgets(vararg widget: IGuiWidget<*>) : WidgetList {
        super.addWidgets(*widget)

        return this
    }
}