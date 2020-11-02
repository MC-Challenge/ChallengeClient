/*
 * Challenge Client
 * https://github.com/MC-Challenge/ChallengeClient/
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.challenge.client.ui.hud.customHud

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.hud.customHud.element.IHudElement
import net.challenge.client.ui.hud.customHud.element.IHudPreview
import net.challenge.client.ui.screen.SettingScreen
import net.challenge.client.ui.widget.utils.RenderUtils
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.ScaledResolution
import org.lwjgl.input.Keyboard
import java.awt.Color
import java.util.function.Consumer
import java.util.function.Predicate

/**
 * # GuiCustomHud
 *
 * TODO Doc
 */
class GuiCustomHud : GuiScreen() {

    /**
     * x-dist of the element
     */
    private var xDist: Int = 0

    /**
     * y-dist of the element
     */
    private var yDist: Int = 0

    /**
     * Selected hud-element what is moved around.
     */
    private var draggingElement: IHudElement? = null

    /**
     * Collection of all enabled custom-hud-elements
     */
    private var enabledElements: Set<IHudElement> = ClientCore.hudRenderer.enabledElements

    /**
     * Is the setting screen
     * (lateinit cause of the 'getScaledWidth' call in [SettingScreen])
     */
    var settingScreen: SettingScreen? = null

    override fun initGui() {
        enabledElements = ClientCore.hudRenderer.enabledElements

        super.initGui()
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        val sr = ScaledResolution(mc)
        val text = "Press [S] for settings"
        fontRendererObj.drawString(text, sr.scaledWidth / 2 - fontRendererObj.getStringWidth(text) / 2, sr.scaledHeight/2 - fontRendererObj.FONT_HEIGHT / 2, -1)

        //BlurUtil.blur()
        moveDraggingElement(mouseX, mouseY)
        renderPreview(mouseX, mouseY, partialTicks)

        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun keyTyped(key: Char, keyCode: Int) {
        if (keyCode == Keyboard.KEY_S) {
            settingScreen = SettingScreen()
            mc.displayGuiScreen(settingScreen)
        }

        super.keyTyped(key, keyCode)
    }

    /**
     * Render a preview of all hud-elements.
     *
     * @param mouseX Mouse X position in pixels
     * @param mouseY Mouse Y position in pixels
     * @param partialTicks How much time has elapsed since the last tick, in ticks,
     * for use by display rendering routines (range: 0.0 - 1.0).
     * This field is frozen if the display is paused to eliminate jitter.
     */
    private fun renderPreview(mouseX: Int, mouseY: Int, partialTicks: Float) {
        enabledElements.forEach(Consumer {
            run {
                if (it is IHudPreview)
                    (it as IHudPreview).drawPreview(mouseX, mouseY, partialTicks)
                else
                    it.drawElement(mouseX, mouseY, partialTicks)

                val position = it.position
                RenderUtils.drawRect(position.getX().toFloat(), position.getX().toFloat(), it.getElementWidth().toFloat(), it.getElementHeight().toFloat(), Color(255, 255, 255, 40))
            }
        })
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        updateHeldRenderer(mouseX, mouseY)
        super.mouseClicked(mouseX, mouseY, mouseButton)
    }

    /**
     * If the mouse position is above a element, this held render
     * is assigned and the distance between the position of the
     * element and the mouse is calculated .
     *
     * @param mouseX Mouse X position in pixels
     * @param mouseY Mouse Y position in pixels
     */
    private fun updateHeldRenderer(mouseX: Int, mouseY: Int) {
        draggingElement = enabledElements.stream().filter(MouseOverElement(mouseX, mouseY)).findFirst().orElse(null)

        draggingElement?.let {
            xDist = it.position.getX().toInt() - mouseX
            yDist = it.position.getY().toInt() - mouseY
        }
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {
        draggingElement = null

        super.mouseReleased(mouseX, mouseY, state)
    }

    /**
     * If a render element is selected the position is set
     * to the x y of the mouse position.
     *
     * @param mouseX Mouse X position in pixels
     * @param mouseY Mouse Y position in pixels
     */
    private fun moveDraggingElement(mouseX: Int, mouseY: Int) {
        draggingElement?.let {
            val sr = ScaledResolution(mc)
            val newX = 0.coerceAtLeast((mouseX + xDist).coerceAtMost((sr.scaledWidth - it.getElementWidth()).coerceAtLeast(0)))
            val newY = 0.coerceAtLeast((mouseY + yDist).coerceAtMost((sr.scaledHeight - it.getElementHeight()).coerceAtLeast(0)))

            it.position.set(newX, newY)
        }
    }

    /**
     * find all elements where the mouse is over
     *
     * @param mouseX Mouse X position in pixels
     * @param mouseY Mouse X position in pixels
     */
    private class MouseOverElement(private val mouseX: Int, private val mouseY: Int) : Predicate<IHudElement> {

        override fun test(element: IHudElement): Boolean {
            val x = element.position.getX()
            val y = element.position.getY()

            return mouseX >= x && mouseX <= x + element.getElementWidth() && mouseY >= y && mouseY <= y + element.getElementHeight()
        }
    }
}