package net.challenge.client.features.modules.impl.visual

import net.challenge.client.features.modules.Module
import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VColor
import net.challenge.configu.value.impl.VNumber
import java.awt.Color

// TODO change category
@ModuleInfo("BlockOverlay", "", ModuleCategory.COSMETICS)
object BlockOverlay : Module() {

    //@VTag("Outline")
    //val outline = VColor(Color(0, 0, 255))
    val outline = Color(0, 0, 255)

    //@VTag("Filled")
    //val filled = VColor(Color(0, 0, 255, 20))
    val filled = Color(0, 0, 255, 20)

    @VTag("Outline Width")
    val outlineWidth = VNumber(2.0F, 0.1, 5)
}