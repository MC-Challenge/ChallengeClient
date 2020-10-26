/*
 * Challenge Client
 * https://github.com/MC-Challenge/ChallengeClient/

 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.challenge.client.ui.widget.utils

import net.challenge.client.utils.IScaledResolutionHelper
import net.minecraft.client.gui.Gui
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11
import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin


/**
 * Helpful collection of functions that can be used for 2D rendering
 */
object RenderUtils : IScaledResolutionHelper {

    /**
     * TODO Doc
     */
    fun drawRoundRect(x: Int, y: Int, width: Int, height: Int, cornerRadius: Int, color: Color) {
        Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + height - cornerRadius, color.rgb)
        Gui.drawRect(x + cornerRadius, y, x + width - cornerRadius, y + height, color.rgb)
        Gui.drawRect(x + width - cornerRadius, y + cornerRadius, x + width, y + height - cornerRadius, color.rgb)

        drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color)
        drawArc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color)
        drawArc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 180, 270, color)
        drawArc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90, 180, color)
    }

    fun scissor(x: Double, y: Double, width: Double, height: Double) {
        var x2 = x
        var y2 = y
        var width2 = width
        var height2 = height

        val sr = getScaledResolution()
        val scale = sr.scaleFactor.toDouble()

        y2 = sr.scaledHeight - y2
        x2 *= scale
        y2 *= scale
        width2 *= scale
        height2 *= scale

        GL11.glScissor(x2.toInt(), (y2 - height2).toInt(), width2.toInt(), height2.toInt())
    }

    /**
     * TODO Doc
     */
    private fun drawArc(x: Int, y: Int, radius: Int, startAngle: Int, endAngle: Int, color: Color) {
        GL11.glPushMatrix()
        GL11.glEnable(3042)
        GL11.glDisable(3553)
        GL11.glBlendFunc(770, 771)
        GL11.glColor4f(color.red.toFloat() / 255, color.green.toFloat() / 255, color.blue.toFloat() / 255, color.alpha.toFloat() / 255)

        val tessellator = Tessellator.getInstance()
        val worldRenderer = tessellator.getWorldRenderer()

        worldRenderer.begin(6, DefaultVertexFormats.POSITION)
        worldRenderer.pos(x.toDouble(), y.toDouble(), 0.0).endVertex()

        for (i in (startAngle / 360.0 * 100).toInt()..(endAngle / 360.0 * 100).toInt()) {
            val angle = Math.PI * 2 * i / 100 + Math.toRadians(180.0)
            worldRenderer.pos(x + sin(angle) * radius, y + cos(angle) * radius, 0.0).endVertex()
        }

        tessellator.draw()

        GL11.glEnable(3553)
        GL11.glDisable(3042)
        GL11.glPopMatrix()
    }

    /**
     * TODO Doc
     */
    fun drawRect(x: Float, y: Float, width: Float, height: Float, color: Int) {
        Gui.drawRect(x.toInt(), y.toInt(), (x + width).toInt(), (y + height).toInt(), color)
    }

    fun drawRect(left: Double, top: Double, right: Double, bottom: Double, color: Int) {
        var left = left
        var right = right
        var top = top
        var bottom = bottom

        if (left < right) {
            val i = left.toInt()
            left = right
            right = i.toDouble()
        }

        if (top < bottom) {
            val j = top.toInt()
            top = bottom
            bottom = j.toDouble()
        }

        val f3 = (color shr 24 and 255).toFloat() / 255.0f
        val f = (color shr 16 and 255).toFloat() / 255.0f
        val f1 = (color shr 8 and 255).toFloat() / 255.0f
        val f2 = (color and 255).toFloat() / 255.0f
        val tessellator = Tessellator.getInstance()
        val worldrenderer = tessellator.worldRenderer
        GlStateManager.enableBlend()
        GlStateManager.disableTexture2D()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.color(f, f1, f2, f3)
        worldrenderer.begin(7, DefaultVertexFormats.POSITION)
        worldrenderer.pos(left, bottom, 0.0).endVertex()
        worldrenderer.pos(right, bottom, 0.0).endVertex()
        worldrenderer.pos(right, top, 0.0).endVertex()
        worldrenderer.pos(left, top, 0.0).endVertex()
        tessellator.draw()
        GlStateManager.enableTexture2D()
        GlStateManager.disableBlend()
    }

    /**
     * TODO Doc
     */
    fun drawRect(x: Float, y: Float, width: Float, height: Float, color: Color) {
        drawRect(x, y, width, height, color.rgb)
    }

    fun drawCircle(x: Double, y: Double, radius: Float, color: Int) {
        val alpha = (color shr 24 and 0xFF) / 255.0f
        val red = (color shr 16 and 0xFF) / 255.0f
        val green = (color shr 8 and 0xFF) / 255.0f
        val blue = (color and 0xFF) / 255.0f

        GL11.glColor4f(red, green, blue, alpha)
        GL11.glEnable(3042)
        GL11.glDisable(3553)
        GL11.glBlendFunc(770, 771)
        GL11.glEnable(2848)
        GL11.glPushMatrix()
        GL11.glLineWidth(1.0f)
        GL11.glBegin(9)

        for (i in 0..360) {
            GL11.glVertex2d(x + sin(i * 3.141592653589793 / 180.0) * radius, y + cos(i * 3.141592653589793 / 180.0) * radius)
        }

        GL11.glEnd()
        GL11.glPopMatrix()
        GL11.glEnable(3553)
        GL11.glDisable(3042)
        GL11.glDisable(2848)
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
    }

    fun drawVerticalLine(x: Double, y: Double, x1: Double, y1: Double, width: Float, color: Int) {
        val red = (color shr 24 and 0xFF) / 255.0f
        val green = (color shr 16 and 0xFF) / 255.0f
        val blue = (color shr 8 and 0xFF) / 255.0f
        val alpha = (color and 0xFF) / 255.0f
        GlStateManager.enableBlend()
        GlStateManager.disableTexture2D()
        GlStateManager.color(red, green, blue, alpha)
        GL11.glLineWidth(width)
        GL11.glBegin(3)
        GL11.glVertex3d(x, y, 0.0)
        GL11.glVertex3d(x1, y1, 0.0)
        GL11.glEnd()
        GlStateManager.enableTexture2D()
        GlStateManager.disableBlend()
    }

}