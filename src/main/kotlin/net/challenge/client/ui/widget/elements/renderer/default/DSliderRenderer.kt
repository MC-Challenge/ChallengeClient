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

import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.font.fancy.GLFont
import net.challenge.client.ui.widget.elements.Slider
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.challenge.client.ui.widget.utils.RenderUtils
import net.minecraft.client.Minecraft
import java.awt.Color
import kotlin.math.max
import kotlin.math.min

/**
 * Default renderer for a slider
 */
class DSliderRenderer : IWidgetRenderer<Slider> {

    // TODO: get this piece of garbage in an separate manager/handler
    private var standardFont: GLFont = FontHandler.getFancyFontRenderer("raleway/raleway-medium", 16)

    override fun render(widget: Slider, mouseX: Int, mouseY: Int) {
        val x = widget.position.getAbsoluteX().toFloat()
        val y = widget.position.getAbsoluteY().toFloat()

        val width = widget.width.toFloat()
        val height = widget.height.toFloat()

        RenderUtils.drawRect(x, y, width, height, Color(35, 34, 35))
        val y2 = y + height - 3.0F
        RenderUtils.drawRect(x, y2, (widget.getAsPercent().toFloat() * width).coerceAtMost(width), 2.0F, Color.WHITE)
        RenderUtils.drawCircle(x + (widget.getAsPercent() * width).coerceAtMost(width.toDouble()), y2 + 1.0, 2F, Color.WHITE.rgb)

        standardFont.drawString(widget.name, x.toDouble() + 2, y.toDouble() + 1, Color.WHITE.rgb)

        var value = widget.value.toString()

        if (widget.isPercent)
            value += "%"

        standardFont.drawStringWithShadow(value, x + width - 3 - standardFont.getWidth(value).toDouble(), y.toDouble() + 2, Color.WHITE.rgb)
    }
}