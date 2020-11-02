package net.challenge.client.ui.widget.elements.renderer.polygons

import net.challenge.client.ui.widget.elements.Rect
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.challenge.client.ui.widget.utils.RenderUtils
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.OpenGlHelper
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11.*


/**
 * Default button renderer
 */
class RectRenderer : IWidgetRenderer<Rect> {

    override fun render(widget: Rect, mouseX: Int, mouseY: Int) {

        // Defines a variable
        // for each value
        var x = widget.position.getX()
        var y = widget.position.getY()
        val width = widget.width
        val height = widget.height
        var secondPosX = x + width
        var secondPosY = y + height
        var color = widget.color

        if (widget.isHover(mouseX, mouseY)) {
            widget.hoverColor?.let {
                color = it
            }
        }

        if (x < secondPosX) {
            val i: Int = x
            x = secondPosX
            secondPosX = i
        }

        if (y < secondPosY) {
            val j: Int = y
            y = secondPosY
            secondPosY = j
        }

        val tessellator = Tessellator.getInstance()
        val worldRenderer = tessellator.worldRenderer

        glEnable(GL_BLEND)
        glDisable(GL_TEXTURE_2D)
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        RenderUtils.color(color)

        worldRenderer.begin(7, DefaultVertexFormats.POSITION)
        worldRenderer.pos(x.toDouble(), secondPosY.toDouble(), 0.0).endVertex()
        worldRenderer.pos(secondPosX.toDouble(), secondPosY.toDouble(), 0.0).endVertex()
        worldRenderer.pos(secondPosX.toDouble(), y.toDouble(), 0.0).endVertex()
        worldRenderer.pos(x.toDouble(), y.toDouble(), 0.0).endVertex()
        tessellator.draw()

        glEnable(GL_TEXTURE_2D)
        glDisable(GL_BLEND)
    }
}