package net.challenge.client.injection.mixins.renderer;


import net.challenge.client.core.ClientCore;
import net.challenge.client.events.RenderPlayerEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
@SideOnly(Side.CLIENT)
public abstract class MixinRenderPlayer extends RendererLivingEntity<AbstractClientPlayer> {

    public MixinRenderPlayer(RenderManager renderManager, ModelBase modelBase, float p_i46156_3_) {
        super(renderManager, modelBase, p_i46156_3_);
    }

    @Inject(method = "doRender", at = @At("HEAD"), cancellable = true)
    public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo callbackInfo) {
        GlStateManager.resetColor();
        final RenderPlayerEvent event = new RenderPlayerEvent(entity, renderManager, x, y, z, partialTicks);
        ClientCore.INSTANCE.getEventBus().post(event);
        ClientCore.INSTANCE.getCosmeticRegistry().renderActivated(entity, x, y, z, partialTicks);
    }
}
