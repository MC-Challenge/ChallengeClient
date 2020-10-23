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

package net.challenge.client.ui.widget.interaction

import net.challenge.client.ui.widget.IGuiWidget

/**
 * This gives an IWidget the function to be selected
 */
interface ISelectable<W : IGuiWidget<*>> {

    /**
     * Registered select listeners.
     * This listener will be executed after a another option is selected for the widget
     */
    val selectListeners: MutableCollection<(W) -> Unit>

    /**
     * Register a listener to respond to a click
     *
     * @param listener This listener will be executed after a another option is selected for the widget
     */
    fun registerSelectListener(listener: (W) -> Unit) {
        selectListeners.add(listener)
    }

    /**
     * Register a listener to respond to a change from another option
     *
     * @param listener This listener will be executed after a another option is selected for the widget
     * @return this
     */
    fun onSelect(listener: (W) -> Unit) : W {
        registerSelectListener(listener)

        @Suppress("UNCHECKED_CAST")
        return this as W
    }
}