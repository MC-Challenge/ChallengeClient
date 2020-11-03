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
import net.challenge.client.ui.widget.elements.Checkbox
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.challenge.client.ui.widget.utils.RenderUtils
import net.minecraft.client.gui.Gui
import java.awt.Color

/**
 * Default renderer for a checkbox
 */
class DCheckboxRenderer : IWidgetRenderer<Checkbox> {

    // TODO: get this piece of garbage in an separate manager/handler
    private var standardFont: GLFont = FontHandler.getFancyFontRenderer("raleway/raleway-medium", 16)

    override fun render(widget: Checkbox, mouseX: Int, mouseY: Int) {
        val x = widget.position.getX().toFloat()
        val y = widget.position.getY().toFloat()
        val standardFont = ClientCore.customHud.settingScreen!!.standardFont

        val width = widget.width.toFloat()
        val height = widget.height.toFloat()

        RenderUtils.drawRect(x, y, width, height, Color(35, 34, 35))

        val padding = 13
        val first = 16

        //draws the box
        Gui.drawRect((x + width - first).toInt(), (y + 1).toInt(), (x + width - (first + padding)).toInt(), (y + height - 1).toInt(), Color(15, 109, 212).rgb)

        if(widget.value) {

            //is the width-add-factor
            val widthAddition = 13.4

            //draws the first Hook
            RenderUtils.drawVerticalLine(x + width -4- widthAddition, y + 4.0, x + width - 10 - widthAddition, y + height - 5.0, 2.0F, Color(250, 255, 255).rgb)

            //draws the second Hook
            RenderUtils.drawVerticalLine(x + width - 10 - widthAddition, y + height - 5.0, x + width - 13 - widthAddition, y + height - 8.0, 2.0F, Color(250, 255, 255).rgb)

        }

        val fontColor = Color.WHITE

        standardFont.drawStringWithShadow(widget.name, x + 10.0, y.toDouble(), fontColor.rgb)
    }
}