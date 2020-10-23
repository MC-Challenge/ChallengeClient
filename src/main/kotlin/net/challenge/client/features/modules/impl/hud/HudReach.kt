package net.challenge.client.features.modules.impl.hud

import me.zero.alpine.event.EventPriority
import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.EventHook
import me.zero.alpine.listener.Listener
import net.challenge.client.events.AttackEntityEvent
import net.challenge.client.features.modules.HudModule
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.client.features.modules.impl.challenge.ReachChallenge
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.hud.customHud.element.IHudPreview
import net.minecraft.util.Vec3
import java.awt.Color
import java.text.DecimalFormat

@ModuleInfo("Reach-Display")
object HudReach : HudModule(), IHudPreview {

    private var lastAttack: Long = 0

    private const val hasntAttacked = "Hasn't attacked"

    /**
     * Reach from player to targetEntity
     */
    private var reach: String? = null

    private var textToRender: String = hasntAttacked

    @EventHandler
    private val attackEntityListener: Listener<AttackEntityEvent> = Listener(
            EventHook {
                if (it.isCancelled) return@EventHook

                lastAttack = System.currentTimeMillis();

                val positionEyes: Vec3 = ReachChallenge.mc.renderViewEntity.getPositionEyes(1.0f)
                reach = DecimalFormat("#.##").format(mc.objectMouseOver.hitVec.distanceTo(positionEyes)) + " blocks"

            }, EventPriority.LOWEST
    )

    override fun drawElement(mouseX: Int, mouseY: Int, partialTicks: Float) {
        if (reach != null && System.currentTimeMillis() - lastAttack > 2000L) reach = null

        textToRender = if (reach == null) hasntAttacked else reach as String

        FontHandler.mcFontRenderer.drawString(textToRender, position.getAbsoluteX(), position.getAbsoluteY(), Color.WHITE.rgb)
    }

    override fun drawPreview(mouseX: Int, mouseY: Int, partialTicks: Float) {
        FontHandler.mcFontRenderer.drawString(hasntAttacked, position.getAbsoluteX(), position.getAbsoluteY(), Color.WHITE.rgb)
    }

    override fun getElementWidth(): Int {
        return FontHandler.mcFontRenderer.getStringWidth(hasntAttacked)
    }

    override fun getElementHeight(): Int {
        return FontHandler.mcFontRenderer.getFontHeight()
    }
}