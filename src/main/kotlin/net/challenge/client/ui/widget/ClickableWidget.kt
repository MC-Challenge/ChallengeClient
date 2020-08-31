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

package net.challenge.client.ui.widget

import net.challenge.client.ui.adapter.input.mouse.IGuiMouseClick
import net.challenge.client.ui.widget.interaction.IClickable

/**
 * Default implementation from a widget that can be clicked
 */
open class ClickableWidget<W : ClickableWidget<W>> : Widget<W>(), IClickable<W>, IGuiMouseClick {

    override val clickListeners: MutableCollection<(W) -> Unit> = arrayListOf()

    override fun mouseClick(mouseX: Int, mouseY: Int, mouseButton: Int): Boolean {
        if (!isHover(mouseX, mouseY)) return false

        @Suppress("UNCHECKED_CAST")
        clickListeners.forEach { it.invoke(this as W) }

        return true
    }
}