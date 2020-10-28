package net.challenge.client.ui.widget.elements.list

import net.challenge.client.ui.widget.IGuiWidget
import net.challenge.client.ui.widget.utils.RenderUtils
import net.challenge.client.ui.widget.utils.Spacing
import org.lwjgl.opengl.GL11
import java.awt.Color

interface IItemList<W : IItemList<W>> : IGuiWidget<W> {

    /**
     * Padding of the list.
     */
    val padding: Spacing

    /**
     * Background color of the list
     */
    var backgroundColor: Int

    override fun render(mouseX: Int, mouseY: Int) {
        val x = position.getX().toFloat()
        val y = position.getY().toFloat()

        GL11.glEnable(GL11.GL_SCISSOR_TEST)
        GL11.glScissor(x.toInt(), y.toInt(), width, height)

        RenderUtils.drawRect(x, y, width.toFloat(), height.toFloat(), backgroundColor)

        val xItem = x + padding.left
        var yItem = y + padding.top
        val widthItem = width - padding.left - padding.right
        val length = getListLength() - 1

        for (i in 0..length) {
            val itemHeight = renderItem(i, xItem, yItem, widthItem, mouseX, mouseY)
            yItem += itemHeight
        }

        GL11.glDisable(GL11.GL_SCISSOR_TEST)
    }

    /**
     * All items rendering in [render].
     *
     * @param index Index Position in the list of the rendering item.
     * @param x X-Pos of the item to render.
     * @param y Y-Pos of the item to render.
     * @param width Width of the item to render.
     * @param mouseX
     * @param mouseY
     */
    fun renderItem(index: Int, x: Float, y: Float, width: Float, mouseX: Int, mouseY: Int): Float

    /**
     * Get the length of the list of the rendering items.
     */
    fun getListLength(): Int

    /**
     * Set the spacing.
     *
     * @param top Top spacing.
     * @param right Right spacing.
     * @param bottom Bottom spacing.
     * @param left Left spacing.
     *
     * @return this
     */
    fun setPadding(top: Float = 0.0F, right: Float = 0.0F, bottom: Float = 0.0F, left: Float = 0.0F): W {
        padding.top = top
        padding.right = right
        padding.bottom = bottom
        padding.left = left

        @Suppress("UNCHECKED_CAST")
        return this as W
    }

    fun setBackGroundColor(color: Int): W {
        this.backgroundColor = color

        @Suppress("UNCHECKED_CAST")
        return this as W
    }

}