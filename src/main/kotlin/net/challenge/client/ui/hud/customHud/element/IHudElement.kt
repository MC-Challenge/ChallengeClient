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

package net.challenge.client.ui.hud.customHud.element

import net.challenge.client.ui.position.IPosition

interface IHudElement {

    /**
     * Position of the element
     */
    var position: IPosition

    /**
     * Width of the element
     */
    var width: Int

    /**
     * Height of the element
     */
    var height: Int

    /**
     * x-dist of the element
     */
    var xDist: Int

    /**
     * y-dist of the element
     */
    var yDist: Int

    /**
     * If the element is not visible no implemented input methods are executed and the widget is not rendered anymore
     */
    var visible: Boolean

    /**
     * is if the element is dragging
     */
    var dragging: Boolean

    /**
     * # Rendering
     * This method renders the element
     */
    fun drawElement(mouseX: Int, mouseY: Int, partialTicks: Float)

    fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        if (dragging) {
            manageDragging(mouseX, mouseY)
        }

        if (this is IHudPreview)
            (this as IHudPreview).drawPreview(mouseX, mouseY, partialTicks)
        else
            drawElement(mouseX, mouseY, partialTicks)
    }

    fun manageDragging(mouseX: Int, mouseY: Int) {

        //sets the absolute
        position.setAbsolute(mouseX + xDist, mouseY + yDist)

    }

    /**
     * # Mouse Input
     * This method, is for handling the mouse-input
     */
    fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        //when the mouse, is in an concrete area, drag!
        if (mouseX >= position.getAbsoluteX() && mouseY >= position.getAbsoluteY() && mouseX < position.getAbsoluteX() + width && mouseY < position.getAbsoluteY() + height) {

            //when the mouse-button is left drag, and when not, open
            if (mouseButton == 0) {

                //sets 'dragging' to true
                dragging = true
            }

            //sets the 'xDist'
            xDist = position.getAbsoluteX() - mouseX

            //sets the 'yDist'
            yDist = position.getAbsoluteY() - mouseY
        }
    }

    /**
     * # Mouse Releasing
     * This method, is for handling the mouse-release
     */
    fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {
        dragging = false
    }

}