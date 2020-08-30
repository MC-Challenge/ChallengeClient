package net.challenge.client.injection.mixins

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.screen.TestWidgetScreen
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.Display
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo


@Mixin(Minecraft::class)
@SideOnly(Side.CLIENT)
class MixinMinecraft {

    @Shadow
    var currentScreen: GuiScreen? = null


    @Inject(method = "startGame", at = [At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER)])
    private fun startGame(callbackInfo: CallbackInfo) {
        ClientCore.onPostStart()
    }

    @Inject(method = "createDisplay", at = [At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", shift = At.Shift.AFTER)])
    private fun createDisplay(callbackInfo: CallbackInfo) {
        val info = ClientCore.info
        Display.setTitle("${ info.name } | ${ info.version }")
    }

    @Inject(method = "startGame", at = [At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", shift = At.Shift.AFTER)])
    private fun afterMainScreen(callbackInfo: CallbackInfo) {
        TestWidgetScreen().open()
    }
}