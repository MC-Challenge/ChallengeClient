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

import net.challenge.client.ui.adapter.input.mouse.IGuiMouseClick
import net.challenge.client.ui.widget.IGuiWidget

/**
 * This gives an IWidget the feature to be clicked.
 */
interface IClickable<W : IGuiWidget> : IGuiMouseClick {

    /**
     * Registered click listeners.
     * This listener will be executed after every click on the widget
     */
    val clickListeners: MutableCollection<(W) -> Unit>

    /**
     * Register a listener to respond to a click
     *
     * @param listener This listener will be executed after registration with every click on the widget
     */
    fun registerClickListener(listener: (W) -> Unit) {
        clickListeners.add(listener)
    }

    /**
     * Register a listener to respond to a click
     *
     * @param listener This listener will be executed after registration with every click on the widget
     * @return this
     */
    fun onClick(listener: (W) -> Unit) : W {
        registerClickListener(listener)

        @Suppress("UNCHECKED_CAST")
        return this as W
    }
}
