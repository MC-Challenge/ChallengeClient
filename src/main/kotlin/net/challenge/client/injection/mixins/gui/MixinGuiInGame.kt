package net.challenge.client.injection.mixins.gui

import net.challenge.client.core.ClientCore
import net.challenge.client.events.Render2DEvent
import net.minecraft.client.gui.GuiIngame
import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(GuiIngame::class)
@SideOnly(Side.CLIENT)
class MixinGuiInGame {

    // TODO is the injection correct? TEST!
    @Inject(method = "renderTooltip", at = [(At("HEAD"))])
    private fun renderTooltip(sr: ScaledResolution, partialTicks: Float, callbackInfo: CallbackInfo) {
        ClientCore.eventBus.post(Render2DEvent(sr, partialTicks))
    }
}