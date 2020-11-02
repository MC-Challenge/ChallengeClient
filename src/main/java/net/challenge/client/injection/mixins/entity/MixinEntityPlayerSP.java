package net.challenge.client.injection.mixins.entity;

import net.challenge.client.core.ClientCore;
import net.challenge.client.events.LivingUpdateEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
@SideOnly(Side.CLIENT)
public class MixinEntityPlayerSP {

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    public void onLivingUpdate(CallbackInfo callbackInfo) {
        ClientCore.INSTANCE.getEventBus().post(new LivingUpdateEvent());
    }
}
