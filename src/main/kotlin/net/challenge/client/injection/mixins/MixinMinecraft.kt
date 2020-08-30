package net.challenge.client.injection.mixins

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.screen.TestWidgetScreen
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(Minecraft::class)
@SideOnly(Side.CLIENT)
class MixinMinecraft {

    @Inject(method = "startGame", at = [At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER)])
    private fun startGame(callbackInfo: CallbackInfo) {
        ClientCore.onPostStart()

        Minecraft.getMinecraft().displayGuiScreen(TestWidgetScreen())
    }
}