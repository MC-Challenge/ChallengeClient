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
import net.challenge.client.ui.widget.elements.Checkbox
import net.challenge.client.ui.widget.elements.Slider
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiSelectWorld
import java.awt.Color

/**
 * Screen to test the widgets
 */
class TestWidgetScreen(lastScreen: GuiScreen? = null) : WidgetScreen(lastScreen) {

    init {
        val width = 80
        val height = 15

        addWidgets(
                Button("SelectWorld")
                        .setPosition(ScaledPosition(30, 5))
                        .setSize(width, height),
                Slider("Value")
                        .setPosition(ScaledPosition(30, 10 + height))
                        .setMaximum(1000.0)
                        .setValue(1000.0)
                        .setSize(width, height)
                        .onSelect {
                            println("Set value to ${it.value}")
                        },
                Checkbox("CheckBox")
                        .setPosition(ScaledPosition(30, 15 + height * 2))
                        .setSize(width, height)
                        .setValue(true)
                        .onSelect {
                            println("Set box to " + it.value)
                        },
                Button("Back ->")
                        .setPosition(ScaledPosition(30, 20 + height * 3))
                        .setSize(width, height)
        )
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        Gui.drawRect(0, 0, width, height, Color.BLACK.rgb)

        super.drawScreen(mouseX, mouseY, partialTicks)
    }
}