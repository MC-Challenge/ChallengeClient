package net.challenge.client.features.modules

import net.challenge.client.ui.hud.customHud.element.IHudElement
import net.challenge.client.ui.position.IPosition
import net.challenge.client.ui.position.ScaledPosition

abstract class HudModule : Module(), IHudElement {

    override var position: IPosition = ScaledPosition(10, 10)
}