/*
 * Challenge Client
 * https://github.com/MC-Challenge/ChallengeClient/

 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.challenge.client.features.modules.impl.player

import me.zero.alpine.event.EventPriority
import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.EventHook
import me.zero.alpine.listener.Listener
import net.challenge.client.events.KeyEvent
import net.challenge.client.events.LivingUpdateEvent
import net.challenge.client.features.modules.HudModule
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.client.ui.hud.customHud.element.IHudPreview
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool
import java.util.function.Predicate

@ModuleInfo("ToggleSprint")
object ToggleSprint : HudModule(), IHudPreview {

    @VTag("Always", "Always sprinting")
    private val always = VBool(false)

    @VTag("Text", "Show status text")
    private val text = VBool(true)

    private var snapped = false

    @EventHandler
    private val livingUpdateListener: Listener<LivingUpdateEvent> = Listener(
            EventHook {
                mc.thePlayer.isSprinting = true
            },

            EventPriority.DEFAULT,

            // Filter
            Predicate {
                mc.thePlayer.foodStats.foodLevel > 6
                mc.gameSettings.keyBindForward.isKeyDown
                !mc.gameSettings.keyBindSneak.isKeyDown
                always.value || snapped
            }
    )

    @EventHandler
    private val keyListener: Listener<KeyEvent> = Listener(
            EventHook {
                if (it.key != mc.gameSettings.keyBindSprint.keyCode) return@EventHook

                snapped = !snapped
            }
    )

    override fun drawElement(mouseX: Int, mouseY: Int, partialTicks: Float) {
        if (!text.value) return

        var status = ""

        if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            status = "[Sneaking (Key Held)]"
        } else if (always.value || snapped) {
            status = "[Sprinting (Toggled)]"
        }

        mc.fontRendererObj.drawString(status, position.getX().toInt(), position.getY().toInt(), -1)
    }

    override fun drawPreview(mouseX: Int, mouseY: Int, partialTicks: Float) {
        mc.fontRendererObj.drawString("[Sneaking (Key Held)]", position.getX().toInt(), position.getY().toInt(), -1)
    }

    override fun getElementWidth(): Int {
        return mc.fontRendererObj.getStringWidth("[Sprinting (Toggled)]")
    }

    override fun getElementHeight(): Int {
        return mc.fontRendererObj.FONT_HEIGHT
    }
}