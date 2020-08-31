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

package net.challenge.client.ui.adapter.input.mouse

import net.challenge.client.ui.widget.IGuiWidget
import net.minecraft.client.gui.GuiScreen

/**
 * This can be implemented in a [IGuiWidget] if you want to have an adapter to the method [GuiScreen.mouseClicked]
 */
interface IGuiMouseClick {

    /**
     * Called when the mouse is clicked.
     *
     * @param mouseX Mouse X position in pixel.
     * @param mouseY Mouse Y position in pixel.
     * @param mouseButton The button that was clicked on the mouse.
     *
     * @return TODO Doc
     */
    fun mouseClick(mouseX: Int, mouseY: Int, mouseButton: Int): Boolean
}