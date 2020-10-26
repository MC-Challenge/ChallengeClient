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

/**
 * Render a font on the screen
 */
interface IFontRenderer {

    /**
     * Draws the specified string with a shadow
     *
     * @param text Text to render
     * @param x Position-X of the text
     * @param y Position-Y of the text
     * @param color Color of the text
     *
     * @return Width of the text
     */
    fun drawStringWithShadow(text: String, x: Float, y: Float, color: Int): Int

    /**
     * Draws the specified string
     *
     * @param text Text to render
     * @param x Position-X of the text
     * @param y Position-Y of the text
     * @param color Color of the text
     *
     * @return Width of the text
     */
    fun drawString(text: String, x: Int, y: Int, color: Int): Int

    /**
     * Returns the width of this string
     *
     * @param text
     * @return Width of the text
     */
    fun getStringWidth(text: String): Int

    /**
     * Get the font-height
     *
     * @return font-height
     */
    fun getFontHeight(s: String): Int

    fun getFontHeight(): Int
}