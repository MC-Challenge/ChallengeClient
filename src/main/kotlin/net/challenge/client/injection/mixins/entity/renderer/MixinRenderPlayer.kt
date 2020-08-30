package net.challenge.client.injection.mixins.entity.renderer

import net.challenge.client.core.ClientCore
import net.challenge.client.events.RenderPlayerEvent
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelPlayer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.entity.RenderPlayer
import net.minecraft.client.renderer.entity.RendererLivingEntity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(RenderPlayer::class)
@SideOnly(Side.CLIENT)
abstract class MixinRenderPlayer(renderManagerIn: RenderManager, modelBaseIn: ModelBase, shadowSizeIn: Float) : RendererLivingEntity<AbstractClientPlayer>(renderManagerIn, modelBaseIn, shadowSizeIn) {

    @Shadow
    abstract override fun getMainModel(): ModelPlayer?

    @Inject(method = "doRender", at = [At("HEAD")], cancellable = true)
    private fun doRender(entity: AbstractClientPlayer, x: Double, y: Double, z: Double, entityYaw: Float, partialTicks: Float, ci: CallbackInfo) {
        GlStateManager.resetColor()
        val event = RenderPlayerEvent(entity, renderManager, x, y, z, partialTicks)
        ClientCore.eventBus.post(event)
        ClientCore.cosmeticRegistry.renderActivated(entity, x, y, z, partialTicks)
    }

}