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
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.ScaledResolution
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
    private var enabledElements: Collection<IHudElement> = ClientCore.hudRenderer.enabledElements


    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        Gui.drawRect(0, 0, mc.displayWidth, mc.displayHeight, Integer.MIN_VALUE)
        moveDraggingElement(mouseX, mouseY)
        renderPreview(mouseX, mouseY, partialTicks)

        println(ClientCore.hudRenderer.enabledElements)

        super.drawScreen(mouseX, mouseY, partialTicks)
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

        if (draggingElement == null) return

        xDist = draggingElement!!.position.getAbsoluteX() - mouseX
        yDist = draggingElement!!.position.getAbsoluteY() - mouseY
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
        if (draggingElement == null) return

        val sr = ScaledResolution(mc)
        val newX = 0.coerceAtLeast((mouseX + xDist).coerceAtMost((sr.scaledWidth - draggingElement!!.getElementWidth()).coerceAtLeast(0)))
        val newY = 0.coerceAtLeast((mouseY + yDist).coerceAtMost((sr.scaledHeight - draggingElement!!.getElementHeight()).coerceAtLeast(0)))

        draggingElement!!.position.setAbsolute(newX, newY)
    }

    /**
     * find all elements where the mouse is over
     *
     * @param mouseX Mouse X position in pixels
     * @param mouseY Mouse X position in pixels
     */
    private class MouseOverElement(private val mouseX: Int, private val mouseY: Int) : Predicate<IHudElement> {

        override fun test(element: IHudElement): Boolean {
            val x: Int = element.position.getAbsoluteX()
            val y: Int = element.position.getAbsoluteY()

            return mouseX >= x && mouseX <= x + element.getElementWidth() && mouseY >= y && mouseY <= y + element.getElementHeight()
        }
    }
}