/*
 * Challenge Client
 * https://github.com/MC-Challenge/ChallengeClient/
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.challenge.client.injection.mixins.client;

import net.challenge.client.core.ClientCore;
import net.challenge.client.core.info.IClientInfo;
import net.challenge.client.events.WorldEvent;
import net.challenge.client.ui.animation.AnimationUtil;
import net.challenge.client.ui.hud.customHud.GuiCustomHud;
import net.challenge.client.ui.screen.TestWidgetScreen;
import net.challenge.client.utils.BlurUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
@SideOnly(Side.CLIENT)
public class MixinMinecraft {

    @Shadow
    public GuiScreen currentScreen;

    @Shadow
    public Timer timer;

    @Shadow
    public Framebuffer framebufferMc;

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER))
    private void startGame(CallbackInfo callbackInfo) {
        ClientCore.INSTANCE.onPostStart();
        BlurUtil.INSTANCE.setFramebuffer(framebufferMc);
    }

    @Inject(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", shift = At.Shift.AFTER))
    private void createDisplay(CallbackInfo callbackInfo) {
        final IClientInfo info = ClientCore.INSTANCE.getInfo();
        Display.setTitle(String.format("%s | %s", info.getName(), info.getVersion()));
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", shift = At.Shift.AFTER))
    private void afterMainScreen(CallbackInfo callbackInfo) {
        Minecraft.getMinecraft().displayGuiScreen(new TestWidgetScreen());
    }

    @Inject(method = "runGameLoop", at = @At(value = "HEAD"))
    private void runGameLoop(CallbackInfo callbackInfo) {
        final int deltaTime = ((int) Minecraft.getSystemTime() - (int) AnimationUtil.INSTANCE.getDeltaTime());

        AnimationUtil.INSTANCE.setLastFrame((float) Minecraft.getSystemTime());
        AnimationUtil.INSTANCE.setDeltaTime((float) deltaTime);

        BlurUtil.INSTANCE.setRenderPartialTicks(timer.renderPartialTicks);
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER))
    private void runTick(CallbackInfo callbackInfo) {
        final int key = Keyboard.getEventKey();

        if (key == Keyboard.KEY_RSHIFT)
            Minecraft.getMinecraft().displayGuiScreen(new GuiCustomHud());
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    private void shutdown(CallbackInfo callbackInfo) {
        ClientCore.INSTANCE.onShutdown();
    }

    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
    private void loadWorld(WorldClient world, String p_loadWorld_2_, final CallbackInfo callbackInfo) {
        ClientCore.INSTANCE.getEventBus().post(new WorldEvent(world));
    }
}
