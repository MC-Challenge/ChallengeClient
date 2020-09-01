package net.challenge.client.ui.hud.customHud.renderer

import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.EventHook
import me.zero.alpine.listener.Listenable
import me.zero.alpine.listener.Listener
import net.challenge.client.core.ClientCore
import net.challenge.client.events.Render2DEvent
import net.challenge.client.ui.hud.customHud.GuiCustomHud
import net.challenge.client.ui.hud.customHud.element.IHudElement
import net.challenge.client.utils.IMC
import java.util.function.Predicate

object HudRenderer : IHudRenderer, Listenable, IMC {

    override var enabledElements: Collection<IHudElement> = ArrayList()

    @EventHandler
    private val render2DListener: Listener<Render2DEvent> = Listener(EventHook {
        // TODO add correct params
        renderHudElements(0, 0, 0.0F)
    },
            // Filter
            Predicate {
                mc.currentScreen !is GuiCustomHud
            }
    )


    init {
        ClientCore.eventBus.subscribe(this)
    }


    override fun renderHudElements(mouseX: Int, mouseY: Int, partialTicks: Float) {
        enabledElements.forEach {
            run {
                it.drawElement(mouseX, mouseY, partialTicks)
            }
        }
    }
}