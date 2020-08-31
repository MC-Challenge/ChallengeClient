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

import net.challenge.client.ui.position.ScaledPosition
import net.challenge.client.ui.widget.elements.Button
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiMainMenu
import net.minecraft.client.gui.GuiSelectWorld
import java.awt.Color

/**
 * Screen to test the widgets
 */
class TestWidgetScreen : WidgetScreen() {

    init {
        addWidgets(
                Button("MainMenu")
                        .setPosition(ScaledPosition.fromRelativePosition(0.5, 0.5))
                        .setSize(100, 40)
                        .onClick {
                            mc.displayGuiScreen(GuiMainMenu())
                        },

                Button("SelectWorld")
                        .setPosition(ScaledPosition.fromRelativePosition(0.2, 0.2))
                        .setSize(100, 40)
                        .onClick {
                            mc.displayGuiScreen(GuiSelectWorld(this))
                        }
        )
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        Gui.drawRect(0, 0, width, height, Color.BLACK.rgb)

        super.drawScreen(mouseX, mouseY, partialTicks)
    }
}