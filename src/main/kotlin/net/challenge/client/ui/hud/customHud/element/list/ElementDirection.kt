/*
 * Challenge Client
 * https://github.com/MC-Challenge/ChallengeClient/
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.challenge.client.ui.hud.customHud.element.list

import net.challenge.client.ui.hud.customHud.element.IHudElement
import net.challenge.client.ui.position.IPosition
import net.challenge.client.ui.position.ScaledPosition
import net.minecraft.client.gui.Gui

class ElementDirection : IHudElement {
    override var position: IPosition = ScaledPosition(10, 10)
    override var width: Int = 10
    override var height: Int = 10
    override var xDist: Int = 0
    override var yDist: Int = 0
    override var visible: Boolean = true
    override var dragging: Boolean = false

    override fun drawElement(mouseX: Int, mouseY: Int, partialTicks: Float) {
        Gui.drawRect(position.getAbsoluteX(), position.getAbsoluteY(), position.getAbsoluteX() + width, position.getAbsoluteY() + height, -1)
    }
}