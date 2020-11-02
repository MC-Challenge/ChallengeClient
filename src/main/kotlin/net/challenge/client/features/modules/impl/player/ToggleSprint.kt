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

import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.EventHook
import me.zero.alpine.listener.Listener
import net.challenge.client.events.KeyEvent
import net.challenge.client.events.LivingUpdateEvent
import net.challenge.client.features.modules.Module
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool

@ModuleInfo("ToggleSprint")
object ToggleSprint : Module() {

    @VTag("Always", "Always sprinting")
    private val always = VBool(false)

    private var snapped = false

    @EventHandler
    private val livingUpdateListener: Listener<LivingUpdateEvent> = Listener(
            EventHook {
                val player = mc.thePlayer
                val settings = mc.gameSettings

                if (player.foodStats.foodLevel < 7) return@EventHook
                if (!settings.keyBindForward.isKeyDown) return@EventHook
                if (settings.keyBindSneak.isKeyDown) return@EventHook
                if (!always.value && !snapped) return@EventHook

                mc.thePlayer.isSprinting = true
            }
    )

    @EventHandler
    private val keyListener: Listener<KeyEvent> = Listener(
            EventHook {
                if (it.key != mc.gameSettings.keyBindSprint.keyCode) return@EventHook

                snapped = !snapped
            }
    )
}