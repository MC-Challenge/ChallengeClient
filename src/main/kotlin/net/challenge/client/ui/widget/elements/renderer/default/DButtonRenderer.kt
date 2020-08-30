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

package net.challenge.client.ui.widget.elements.renderer.default

import net.challenge.client.ui.widget.elements.Button
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import java.awt.Color

/**
 * Default button renderer
 */
class DButtonRenderer : IWidgetRenderer<Button> {

    override fun render(widget: Button, mouseX: Int, mouseY: Int) {
        val x = widget.position.getAbsoluteX()
        val y = widget.position.getAbsoluteY()

        Gui.drawRect(x, y, x + widget.width, y + widget.height, Color.WHITE.rgb)
        Minecraft.getMinecraft().fontRendererObj.drawString(widget.name, x, y, Color.RED.rgb)
    }
}