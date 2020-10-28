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

package net.challenge.client.ui.screen

import net.challenge.client.ui.adapter.IGuiRender
import net.challenge.client.ui.screen.container.WidgetContainer
import net.challenge.client.ui.widget.IGuiWidget
import net.challenge.client.ui.widget.utils.RenderUtils
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.input.Mouse
import org.lwjgl.opengl.Display
import java.awt.Color

/**
 * A bridge between [GuiScreen] and the [WidgetContainer]
 */
open class WidgetScreen(

        /**
         * The screen that was open before this screen.
         */
        private val lastScreen: GuiScreen? = null

) : GuiScreen(), IGuiRender {

    private val widgetContainer = WidgetContainer()

    override fun initGui() {
        // TODO Bridge between widget and init event
        super.initGui()
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        GlStateManager.pushMatrix()

        val sr = ScaledResolution(Minecraft.getMinecraft())

        when (sr.scaleFactor) {
            2 -> {
                GlStateManager.scale(0.5, 0.5, 0.5)
            }
            3 -> {
                GlStateManager.scale(0.3325, 0.3325, 0.3325)
            }
            4 -> {
                GlStateManager.scale(0.25, 0.25, 0.25)
            }
        }

        render(getMouseX(), getMouseY())

        GlStateManager.popMatrix()
    }

    override fun render(mouseX: Int, mouseY: Int) {
        widgetContainer.render(mouseX, mouseY)
    }

    fun getMouseX(): Int {
        return Mouse.getX()
    }

    fun getMouseY(): Int {
        return -Mouse.getY() + Display.getHeight()
    }

    override fun handleMouseInput() {
        widgetContainer.handleMouseInput()

        super.handleMouseInput()
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (widgetContainer.mouseClick(getMouseX(), getMouseY(), mouseButton)) return

        super.mouseClicked(mouseX, mouseY, mouseButton)
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {
        widgetContainer.mouseRelease(getMouseX(), getMouseY(), state)

        super.mouseReleased(mouseX, mouseY, state)
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        widgetContainer.keyType(typedChar, keyCode)

        super.keyTyped(typedChar, keyCode)
    }

    override fun mouseClickMove(mouseX: Int, mouseY: Int, clickedMouseButton: Int, timeSinceLastClick: Long) {
        widgetContainer.mouseClickAndMove(getMouseX(), getMouseY(), clickedMouseButton, timeSinceLastClick)
    }

    override fun updateScreen() {
        widgetContainer.updateScreen()

        super.updateScreen()
    }

    /**
     * Register widgets to the [WidgetContainer]
     *
     * @param widget This widget will be added to the [WidgetContainer]
     */
    fun addWidgets(vararg widget: IGuiWidget<*>) {
        widgetContainer.addWidgets(*widget)
    }

    /**
     * Open the [lastScreen].
     */
    fun goBack() {
        mc.displayGuiScreen(lastScreen)
    }
}