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
import net.challenge.client.ui.widget.elements.Checkbox
import net.challenge.client.ui.widget.renderer.IWidgetRenderer
import net.challenge.client.ui.widget.utils.RenderUtils
import net.minecraft.client.gui.Gui
import java.awt.Color

/**
 * Default renderer for a checkbox
 */
class DCheckboxRenderer : IWidgetRenderer<Checkbox> {

    override fun render(widget: Checkbox, mouseX: Int, mouseY: Int) {
        val x = widget.position.getX().toFloat()
        val y = widget.position.getY().toFloat()
        val standardFont = ClientCore.customHud.settingScreen!!.standardFont

        val width = widget.width.toFloat()
        val height = widget.height.toFloat()

        RenderUtils.drawRect(x, y, width, height, Color(35, 34, 35))

        val padding = 5
        val length = 20

        //draws the box
        Gui.drawRect((x + width - padding).toInt(), y.toInt(), (x + width - padding - length).toInt(), (y + height - 2).toInt(), Color(15, 109, 212).rgb)

        if (widget.value) {

            //is the width-add-factor
            val widthAddition = length / 3.0
            val widthAdditionFirst = widthAddition + 2
            val heightAdditionFirst = height / 6.0
            val heightAdditionSecond = height / 4.0

            //draws the first Hook
            RenderUtils.drawVerticalLine(x + width - widthAdditionFirst, y + heightAdditionFirst, x + width - 10 - widthAddition, y + height - 5.0, 2.0F, Color(250, 255, 255).rgb)

            //draws the second Hook
            RenderUtils.drawVerticalLine(x + width - 14 - widthAddition, y + heightAdditionSecond, x + width - 10 - widthAddition, y + height - 5.0, 2.0F, Color(250, 255, 255).rgb)

        }

        val fontColor = Color.WHITE

        standardFont.drawStringWithShadow(widget.name, x + 2.0, y.toDouble() + height / 2 - standardFont.height / 2, fontColor.rgb)
    }
}