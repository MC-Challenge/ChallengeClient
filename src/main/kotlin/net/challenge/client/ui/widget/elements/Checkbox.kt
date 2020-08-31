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

import net.challenge.client.ui.adapter.input.mouse.IGuiMouseClick
import net.challenge.client.ui.widget.SelectableWidget

/**
 * Checkbox to change a [Boolean] value
 */
class Checkbox(var name: String) : SelectableWidget<Checkbox>(), IGuiMouseClick {

    var value: Boolean = false
        set(value) {
            field = value

            // Call all Select-Listener
            selectListeners.forEach { it.invoke(this) }
        }

    override fun mouseClick(mouseX: Int, mouseY: Int, mouseButton: Int): Boolean {
        if (isHover(mouseX, mouseY)) {
            value = !value
            return true
        }

        return false
    }

    /**
     * Set the value
     *
     * @param value Value to set
     * @return this
     */
    fun setValue(value: Boolean): Checkbox {
        this.value = value

        return this
    }
}