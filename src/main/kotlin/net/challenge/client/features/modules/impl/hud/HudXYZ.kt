package net.challenge.client.features.modules.impl.hud

import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.features.modules.SimpleHudModule
import net.challenge.client.features.modules.annotations.ModuleInfo

@ModuleInfo(name = "Position", category = ModuleCategory.HUD)
object HudXYZ : SimpleHudModule() {

    override fun getValue(): String {
        val player = mc.thePlayer
        return "${player.posX.toInt()} ${player.posY.toInt()} ${player.posZ.toInt()}"
    }

    override fun getDisplayName(): String {
        return "XYZ"
    }
}