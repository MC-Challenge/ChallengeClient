package net.challenge.client.features.modules.impl.hud

import net.challenge.client.features.modules.SimpleHudModule
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool
import net.minecraft.client.Minecraft
import net.minecraft.entity.Entity

@ModuleInfo(name = "Direction")
object HudDirection : SimpleHudModule() {

    /**
     * Option, if the text only has one character
     */
    @VTag(name = "One Character", description = "Should the text only have one character")
    val oneCharacter = VBool(false)

    override fun getValue(): String {
        val entity: Entity = Minecraft.getMinecraft().renderViewEntity
        val s: String = entity.horizontalFacing.toString()
        oneCharacter.value = false

        return if (oneCharacter.value) s[0].toString().toUpperCase() else s[0].toUpperCase().toString() + s.substring(1).toLowerCase()
    }

    override fun getDisplayName(): String {
        return "Direction"
    }
}