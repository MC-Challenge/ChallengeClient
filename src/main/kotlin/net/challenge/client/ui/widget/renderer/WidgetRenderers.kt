package net.challenge.client.ui.widget.renderer

import net.challenge.client.ui.widget.IWidget
import net.challenge.client.ui.widget.elements.button.Button
import net.challenge.client.ui.widget.elements.renderer.default.DButtonRenderer

/**
 * TODO Doc
 */
object WidgetRenderers {

    private val renderers = mutableMapOf<Class<*>, IWidgetRenderer<*>>()


    init {
        // Set Default Renderers
        setRenderer(Button::class.java, DButtonRenderer())
    }


    fun <W : IWidget> setRenderer(clazz: Class<W>, renderer: IWidgetRenderer<W>) {
        this.renderers[clazz] = renderer
    }

    fun <C : IWidget> getRenderer(clazz: Class<C>): IWidgetRenderer<C>? {
        @Suppress("UNCHECKED_CAST")
        return this.renderers[clazz] as? IWidgetRenderer<C>
    }
}