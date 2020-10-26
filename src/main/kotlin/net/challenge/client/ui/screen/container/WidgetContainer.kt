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
import net.challenge.client.ui.adapter.IGuiGuiEvents
import net.challenge.client.ui.adapter.IGuiUpdate
import net.challenge.client.ui.adapter.input.IGuiKeyType
import net.challenge.client.ui.adapter.input.mouse.IGuiHandleMouseInput
import net.challenge.client.ui.adapter.input.mouse.IGuiMouseClick
import net.challenge.client.ui.adapter.input.mouse.IGuiMouseClickAndMove
import net.challenge.client.ui.adapter.input.mouse.IGuiMouseRelease
import net.challenge.client.ui.widget.IGuiWidget
import java.util.function.Consumer

/**
 * Container for widgets
 * TODO Detailed explanation
 */
open class WidgetContainer : IGuiGuiEvents {

    /**
     * All registered widgets in this container
     */
    var widgets: List<IGuiWidget<*>> = listOf()

    /**
     * A cache for all widgets that are currently visible and not disable.
     */
    protected var interactionWidgets: List<IGuiWidget<*>> = listOf()

    /**
     * A cache for all widgets that are currently visible.
     */
    protected var visibleWidgets: List<IGuiWidget<*>> = listOf()


    override fun render(mouseX: Int, mouseY: Int) {
        updateWidgetsCache()

        visibleWidgets.forEach(Consumer { widget: IGuiWidget<*> ->
            widget.render(mouseX, mouseY)
        })
    }

    /**
     * Cache is updated and reversed the list
     */
    protected fun updateWidgetsCache() {
        visibleWidgets = widgets.filter(IGuiWidget<*>::visible).toList().reversed()
        interactionWidgets = visibleWidgets.filter { !it.disable }.toList()
    }

    override fun onGuiClose() {
        interactionWidgets.forEach(Consumer { widget: IGuiWidget<*> ->
            (widget as IGuiClose).onGuiClose()
        })
    }

    override fun keyType(typedChar: Char, keyCode: Int) {
        interactionWidgets.filter {
            (it is IGuiKeyType)
        }.forEach(Consumer { widget: IGuiWidget<*> ->
            (widget as IGuiKeyType).keyType(typedChar, keyCode)
        })
    }

    override fun handleMouseInput() {
        interactionWidgets.filter {
            (it is IGuiHandleMouseInput)
        }.forEach(Consumer { widget: IGuiWidget<*> ->
            (widget as IGuiHandleMouseInput).handleMouseInput()
        })
    }

    override fun mouseClick(mouseX: Int, mouseY: Int, mouseButton: Int): Boolean {
        var result = false

        interactionWidgets.filter {
            (it is IGuiMouseClick)
        }.forEach(Consumer { widget: IGuiWidget<*> ->
            if ((widget as IGuiMouseClick).mouseClick(mouseX, mouseY, mouseButton))
                result = true
        })

        return result
    }

    override fun mouseClickAndMove(mouseX: Int, mouseY: Int, clickedMouseButton: Int, timeSinceLastClick: Long) {
        interactionWidgets.filter {
            (it is IGuiMouseClickAndMove)
        }.forEach(Consumer { widget: IGuiWidget<*> ->
            (widget as IGuiMouseClickAndMove).mouseClickAndMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick)
        })
    }

    override fun mouseRelease(mouseX: Int, mouseY: Int, mouseButton: Int) {
        interactionWidgets.filter {
            (it is IGuiMouseRelease)
        }.forEach(Consumer { widget: IGuiWidget<*> ->
            (widget as IGuiMouseRelease).mouseRelease(mouseX, mouseY, mouseButton)
        })
    }

    override fun updateScreen() {
        interactionWidgets.filter {
            (it is IGuiUpdate)
        }.forEach(Consumer { widget: IGuiWidget<*> ->
            (widget as IGuiUpdate).updateScreen()
        })
    }

    /**
     * Register widgets
     *
     * @param widget This widget will be added to the [widgets] list
     */
    fun addWidgets(vararg widget: IGuiWidget<*>) {
        widgets = widgets + widget
    }

    /**
     * Deletes all [IGuiWidget] from the [widgets].
     */
    fun clear() {
        widgets = listOf()
    }
}