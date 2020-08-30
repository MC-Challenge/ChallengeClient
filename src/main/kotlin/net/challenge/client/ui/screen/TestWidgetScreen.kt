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

import net.challenge.client.ui.widget.elements.Button
import net.minecraft.client.gui.Gui
import java.awt.Color

/**
 * Screen to test the widgets
 */
class TestWidgetScreen : WidgetScreen() {

    init {
        val btn = Button("Test Button")
        btn.position.setAbsolute(100, 100)
        btn.width = 100
        btn.height = 40

        addWidgets(btn)
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        Gui.drawRect(0, 0, width, height, Color.BLACK.rgb)

        super.drawScreen(mouseX, mouseY, partialTicks)
    }
}