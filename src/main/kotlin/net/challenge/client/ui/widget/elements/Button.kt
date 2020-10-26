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

package net.challenge.client.ui.widget.elements

import net.challenge.client.ui.widget.ClickableWidget
import java.awt.Color

/**
 * Default implementation of a [ClickableWidget]
 */
class Button(val name: String) : ClickableWidget<Button>() {

    var color: Color = Color(50, 50, 50)

    var centered = true

    fun setColor(color: Color) : Button {
        this.color = color
        return this
    }

    fun setCentered(centered: Boolean) : Button {
        this.centered = centered
        return this
    }

}