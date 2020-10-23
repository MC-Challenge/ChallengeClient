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

import net.challenge.client.ui.adapter.IGuiRender
import net.challenge.client.ui.position.IPosition

/**
 * This is the basic structure element of a GUI-Screen.
 * A widget can interact with the user if the desired Gui-Adapters (find in [net.challenge.client.ui.adapter]) are implemented.
 */
interface IGuiWidget<W : IGuiWidget<W>> : IGuiRender {

    /**
     * Position of the widget
     */
    var position: IPosition

    /**
     * Width of the widget
     */
    var width: Int

    /**
     * Height of the widget
     */
    var height: Int

    /**
     * If the widget is not visible no implemented input methods are executed and the widget is not rendered anymore
     */
    var visible: Boolean

    /**
     * If the widget is disable no implemented input methods are executed.
     */
    var disable: Boolean

    /**
    * If the mouse is over the widget.
    *
    * @param mouseX Mouse X position in pixel.
    * @param mouseY Mouse Y position in pixel.
    *
    * @return Is mouse over
    */
    fun isHover(mouseX: Int, mouseY: Int): Boolean {
        val x =  position.getAbsoluteX()
        val y = position.getAbsoluteY()

        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height
    }

    /**
     * Set the position of the widget
     *
     * @param position Position to set
     * @return this
     */
    fun setPosition(position: IPosition) : W {
        this.position = position

        @Suppress("UNCHECKED_CAST")
        return this as W
    }

    /**
     * Set the visibility of the widget
     *
     * @param visible Visibility to set
     * @return this
     */
    fun setVisible(visible: Boolean) : W {
        this.visible = visible

        @Suppress("UNCHECKED_CAST")
        return this as W
    }

    /**
    * Set the disable state of the widget
    *
    * @param disable Disable to set
    * @return this
    */
    fun setDisable(disable: Boolean) : W {
        this.disable = disable

        @Suppress("UNCHECKED_CAST")
        return this as W
    }

    /**
     * Set the width of the widget
     *
     * @param width Width to set
     * @return this
     */
    fun setWidth(width: Int) : W {
        this.width = width

        @Suppress("UNCHECKED_CAST")
        return this as W
    }

    /**
     * Set the height of the widget
     *
     * @param height Height to set
     * @return this
     */
    fun setHeight(height: Int) : W {
        this.height = height

        @Suppress("UNCHECKED_CAST")
        return this as W
    }

    /**
     * Set the width and height of the widget
     *
     * @param width Width to set
     * @param height Height to set
     * @return this
     */
    fun setSize(width: Int, height: Int) : W {
        this.width = width
        this.height = height

        @Suppress("UNCHECKED_CAST")
        return this as W
    }
}