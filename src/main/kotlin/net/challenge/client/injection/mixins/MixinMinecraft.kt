package net.challenge.client.injection.mixins

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.animation.AnimationUtil
import net.challenge.client.ui.hud.customHud.GuiCustomHud
import net.challenge.client.ui.screen.TestWidgetScreen
import net.challenge.client.utils.BlurUtil
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.shader.Framebuffer
import net.minecraft.util.Timer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.input.Keyboard
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

    @Shadow
    var timer: Timer? = null

    @Shadow
    var framebufferMc: Framebuffer? = null

    @Inject(method = "startGame", at = [At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER)])
    private fun startGame(callbackInfo: CallbackInfo) {
        ClientCore.onPostStart()
        BlurUtil.framebuffer = framebufferMc
    }

    @Inject(method = "createDisplay", at = [At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", shift = At.Shift.AFTER)])
    private fun createDisplay(callbackInfo: CallbackInfo) {
        val info = ClientCore.info
        Display.setTitle("${info.name} | ${info.version}")
    }

    @Inject(method = "startGame", at = [At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", shift = At.Shift.AFTER)])
    private fun afterMainScreen(callbackInfo: CallbackInfo) {
        Minecraft.getMinecraft().displayGuiScreen(TestWidgetScreen())
    }

    @Inject(method = "runGameLoop", at = [At(value = "HEAD")])
    private fun runGameLoop(callbackInfo: CallbackInfo) {
        val deltaTime = (Minecraft.getSystemTime() - AnimationUtil.deltaTime).toInt()
        AnimationUtil.lastFrame = Minecraft.getSystemTime().toFloat()
        AnimationUtil.deltaTime = deltaTime.toFloat()

        BlurUtil.renderPartialTicks = timer?.renderPartialTicks
    }

    @Inject(method = "runTick", at = [At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER)])
    private fun runTick(callbackInfo: CallbackInfo) {
        val k: Int = Keyboard.getEventKey()
        if (k == Keyboard.KEY_RSHIFT)
            Minecraft.getMinecraft().displayGuiScreen(GuiCustomHud())
    }
}