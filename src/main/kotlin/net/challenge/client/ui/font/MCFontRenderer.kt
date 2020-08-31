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

package net.challenge.client.ui.font

import net.minecraft.client.gui.FontRenderer

/**
 * Adapter for a MC-[FontRenderer]
 */
class MCFontRenderer(private val fontRenderer: FontRenderer) : IFontRenderer {

    override fun drawStringWithShadow(text: String, x: Float, y: Float, color: Int): Int {
        return fontRenderer.drawStringWithShadow(text, x, y, color)
    }

    override fun drawString(text: String, x: Int, y: Int, color: Int): Int {
        return fontRenderer.drawString(text, x, y, color)
    }

    override fun getStringWidth(text: String): Int {
        return fontRenderer.getStringWidth(text)
    }

    override fun getFontHeight(): Int {
        return fontRenderer.FONT_HEIGHT
    }
}