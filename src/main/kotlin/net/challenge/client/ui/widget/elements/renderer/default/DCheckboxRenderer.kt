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

import net.challenge.client.ui.widget.elements.Checkbox
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.challenge.client.ui.widget.utils.RenderUtils
import net.minecraft.client.Minecraft
import java.awt.Color

/**
 * Default renderer for a checkbox
 */
class DCheckboxRenderer : IWidgetRenderer<Checkbox> {

    override fun render(widget: Checkbox, mouseX: Int, mouseY: Int) {
        val x = widget.position.getAbsoluteX().toFloat()
        val y = widget.position.getAbsoluteY().toFloat()

        val width = widget.width.toFloat()
        val height = widget.height.toFloat()

        RenderUtils.drawRect(x, y, width, height,  Color(50, 50, 50))

        var fontColor = Color.WHITE

        if (widget.value)
            fontColor = Color.GREEN

        val font = Minecraft.getMinecraft().fontRendererObj
        font.drawString(widget.name, x.toInt() + width.toInt()/2 - font.getStringWidth(widget.name) / 2, y.toInt() + height.toInt()/3, fontColor.rgb)
    }
}