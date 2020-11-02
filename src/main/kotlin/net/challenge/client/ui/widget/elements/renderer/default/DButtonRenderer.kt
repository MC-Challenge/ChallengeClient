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

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.font.fancy.GLFont
import net.challenge.client.ui.widget.elements.Button
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.challenge.client.ui.widget.utils.RenderUtils
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
        val standardFont = ClientCore.customHud.settingScreen!!.standardFont

        val hover = widget.isHover(mouseX, mouseY)

        var bgColor = widget.color
        var textColor = Color(200, 200, 200).rgb

        if (hover) {
            bgColor = Color(35, 34, 35)
            textColor = Color.WHITE.rgb
        }


        var textX = x + widget.width / 2 - standardFont.getWidth(widget.name) / 2
        if(!widget.centered) textX = x + 2

        RenderUtils.drawRect(x.toFloat(), y.toFloat(), widget.width.toFloat(), widget.height.toFloat(), bgColor)
        standardFont.drawStringWithShadow(widget.name, textX.toDouble(), y + widget.height / 2 - standardFont.height.toDouble() / 2, textColor)
    }
}