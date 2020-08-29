package net.challenge.client.ui.screen.container

import net.challenge.client.ui.events.IGuiClose
import net.challenge.client.ui.events.IGuiEvents
import net.challenge.client.ui.events.IRender
import net.challenge.client.ui.events.input.IGuiKeyType
import net.challenge.client.ui.events.input.mouse.IGuiHandleMouseInput
import net.challenge.client.ui.events.input.mouse.IGuiMouseClick
import net.challenge.client.ui.events.input.mouse.IGuiMouseClickAndMove
import net.challenge.client.ui.events.input.mouse.IGuiMouseRelease
import net.challenge.client.ui.widget.IWidget
import java.util.function.Consumer

/**
 * TODO Doc
 */
class WidgetContainer : IGuiEvents {

    private var widgets: List<IWidget> = listOf()

    private var visibleWidgets: List<IWidget> = listOf()


    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        visibleWidgets = widgets.filter(IWidget::visible).toList().reversed()

        visibleWidgets.filter {
            (it is IRender)
        }.forEach(Consumer { widget: IWidget ->
            (widget as IRender).render(mouseX, mouseY, partialTicks)
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
}