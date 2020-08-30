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

package net.challenge.client.injection.mixins.gui

import net.challenge.client.core.ClientCore.commandRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(GuiScreen::class)
@SideOnly(Side.CLIENT)
class MixinGuiScreen {
    @Shadow
    var mc: Minecraft? = null
    @Inject(method = "sendChatMessage(Ljava/lang/String;Z)V", at = [At("HEAD")], cancellable = true)
    private fun onChat(msg: String, addToChat: Boolean, ci: CallbackInfo) {
        if (commandRegistry.runCommand(msg)) {
            mc!!.ingameGUI.chatGUI.addToSentMessages(msg)
            ci.cancel()
        }
    }
}