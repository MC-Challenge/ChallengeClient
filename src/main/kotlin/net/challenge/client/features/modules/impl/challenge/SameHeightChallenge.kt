package net.challenge.client.features.modules.impl.challenge

import me.zero.alpine.event.EventPriority
import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.EventHook
import me.zero.alpine.listener.Listener
import net.challenge.client.events.Render2DEvent
import net.challenge.client.features.modules.Module
import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.client.features.teammate.ITeammateHandler
import net.challenge.client.ui.animation.AnimationUtil
import net.challenge.client.ui.widget.utils.RenderUtils
import net.challenge.client.utils.BlurUtil
import net.challenge.client.utils.TimeHelper
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool
import net.challenge.configu.value.impl.VNumber
import net.minecraft.client.gui.ScaledResolution
import java.awt.Color
import java.util.function.Predicate
import kotlin.math.roundToInt

/**
 * All team members must be at the same height,
 * otherwise there is a black screen for everyone.
 */
@ModuleInfo("Challenge-2", "All team members must be at the same height, otherwise there is a black screen for everyone.", ModuleCategory.CHALLENGE)
class SameHeightChallenge(

        /**
         * Handler to manage teammates.
         */
        private val teammateHandler: ITeammateHandler

) : Module() {

    @VTag("OnlyVisible", "Only visible players")
    private val onlyVisible = VBool(true)

    @VTag("TimeToBlack", "Its the time, to wait, until gets black")
    private val timeToBlack = VNumber(0.3, 0.0, 3.0)

    @VTag("Blur", "If its gonna blur, or gets black")
    private val blur = VBool(true)

    private val timeHelper: TimeHelper = TimeHelper()
    private var fade: Double = 0.0

    @EventHandler
    private val render2DListener: Listener<Render2DEvent> = Listener(
            EventHook {
                val sr = ScaledResolution(mc)
                if (timeHelper.hasReached(timeToBlack.value * 1000)) {
                    fade = AnimationUtil.slide(fade, 0.0, if (!blur.value) 250.0 else 150.0, 0.05, true)
                    if (blur.value)
                        BlurUtil.blur(fade.roundToInt().toFloat(), it.partialTicks)
                    else
                        RenderUtils.drawRect(0.0F, 0.0F, sr.scaledWidth.toFloat(), sr.scaledHeight.toFloat(), Color(0, 0, 0, fade.toInt()).rgb)
                }
            },

            EventPriority.LOWEST,

            // Filter
            Predicate {
                teammateNotSamePosY()
            }
    )

    private fun teammateNotSamePosY(): Boolean {
        val playerPosY = mc.thePlayer.posY

        teammateHandler.getPlayers().forEach { teammateName -> // Ja da muss ne Liste hin
            val entity = mc.theWorld.loadedEntityList
                    .firstOrNull { entity -> entity.name.equals(teammateName, true) && (entity.isInvisible.not() || onlyVisible.value.not()) }
                    ?: return@forEach

            if (!entity.name.equals(teammateName, true)) return@forEach
            if (entity.posY.roundToInt() == playerPosY.roundToInt()) {
                timeHelper.reset()
                fade = 0.0
                return@forEach
            }
            return true
        }

        return false
    }
}