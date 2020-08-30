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

import net.challenge.client.ui.screen.container.WidgetContainer
import net.challenge.client.ui.widget.IWidget
import net.minecraft.client.gui.GuiScreen

/**
 * A bridge between [GuiScreen] and the [WidgetContainer]
 */
open class WidgetScreen : GuiScreen() {

    private val widgetContainer = WidgetContainer()

    override fun initGui() {
        // TODO Bridge between widget and init event
        super.initGui()
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        widgetContainer.render(mouseX, mouseY)

        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun handleMouseInput() {
        widgetContainer.handleMouseInput()

        super.handleMouseInput()
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        if (widgetContainer.mouseClick(mouseX, mouseY, mouseButton)) return

        super.mouseClicked(mouseX, mouseY, mouseButton)
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {
        widgetContainer.mouseRelease(mouseX, mouseY, state)

        super.mouseReleased(mouseX, mouseY, state)
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        widgetContainer.keyType(typedChar, keyCode)

        super.keyTyped(typedChar, keyCode)
    }

    /**
     * Register widgets to the [WidgetContainer]
     *
     * @param widget This widget will be added to the [WidgetContainer]
     */
    fun addWidgets(vararg widget: IWidget) {
        widgetContainer.addWidgets(*widget)
    }

    /**
     * Open this screen
     */
    fun open() {
        mc.displayGuiScreen(this)
    }
}