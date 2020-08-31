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

package net.challenge.client.ui.screen.container

import net.challenge.client.ui.adapter.IGuiClose
import net.challenge.client.ui.adapter.IGuiEvents
import net.challenge.client.ui.adapter.IRender
import net.challenge.client.ui.adapter.input.IGuiKeyType
import net.challenge.client.ui.adapter.input.mouse.IGuiHandleMouseInput
import net.challenge.client.ui.adapter.input.mouse.IGuiMouseClick
import net.challenge.client.ui.adapter.input.mouse.IGuiMouseClickAndMove
import net.challenge.client.ui.adapter.input.mouse.IGuiMouseRelease
import net.challenge.client.ui.widget.IWidget
import java.util.function.Consumer

/**
 * Container for widgets
 * TODO Detailed explanation
 */
class WidgetContainer : IGuiEvents {

    /**
     * All registered widgets in this container
     */
    private var widgets: List<IWidget> = listOf()

    /**
     * A cache for all widgets that are currently visible.
     */
    private var visibleWidgets: List<IWidget> = listOf()


    override fun render(mouseX: Int, mouseY: Int) {
        // Cache is updated and reversed the list
        visibleWidgets = widgets.filter(IWidget::visible).toList().reversed()

        visibleWidgets.forEach(Consumer { widget: IWidget ->
            (widget as IRender).render(mouseX, mouseY)
        })
    }

    override fun onGuiClose() {
        visibleWidgets.forEach(Consumer { widget: IWidget ->
            (widget as IGuiClose).onGuiClose()
        })
    }

    override fun keyType(typedChar: Char, keyCode: Int) {
        visibleWidgets.filter {
            (it is IGuiKeyType)
        }.forEach(Consumer { widget: IWidget ->
            (widget as IGuiKeyType).keyType(typedChar, keyCode)
        })
    }

    override fun handleMouseInput() {
        visibleWidgets.filter {
            (it is IGuiHandleMouseInput)
        }.forEach(Consumer { widget: IWidget ->
            (widget as IGuiHandleMouseInput).handleMouseInput()
        })
    }

    override fun mouseClick(mouseX: Int, mouseY: Int, mouseButton: Int): Boolean {
        var result = false

        visibleWidgets.filter {
            (it is IGuiMouseClick)
        }.forEach(Consumer { widget: IWidget ->
            if ((widget as IGuiMouseClick).mouseClick(mouseX, mouseY, mouseButton))
                result = true
        })

        return result
    }

    override fun mouseClickAndMove(mouseX: Int, mouseY: Int, clickedMouseButton: Int, timeSinceLastClick: Long) {
        visibleWidgets.filter {
            (it is IGuiMouseClickAndMove)
        }.forEach(Consumer { widget: IWidget ->
            (widget as IGuiMouseClickAndMove).mouseClickAndMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)
        })
    }

    override fun mouseRelease(mouseX: Int, mouseY: Int, mouseButton: Int) {
        visibleWidgets.filter {
            (it is IGuiMouseRelease)
        }.forEach(Consumer { widget: IWidget ->
            (widget as IGuiMouseRelease).mouseRelease(mouseX, mouseY, mouseButton)
        })
    }

    /**
     * Register widgets
     *
     * @param widget This widget will be added to the [widgets] list
     */
    fun addWidgets(vararg widget: IWidget) {
        widgets = widgets + widget
    }
}