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

package net.challenge.client.ui.hud.customHud.renderer

import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.EventHook
import me.zero.alpine.listener.Listenable
import me.zero.alpine.listener.Listener
import net.challenge.client.core.ClientCore
import net.challenge.client.events.Render2DEvent
import net.challenge.client.ui.hud.customHud.GuiCustomHud
import net.challenge.client.ui.hud.customHud.element.IHudElement
import net.challenge.client.utils.IMC
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution
import org.lwjgl.opengl.GL11
import java.util.function.Predicate

object HudRenderer : IHudRenderer, Listenable, IMC {

    override var enabledElements: Set<IHudElement> = linkedSetOf()

    @EventHandler
    private val render2DListener: Listener<Render2DEvent> = Listener(EventHook {
        // TODO add correct params
        renderHudElements(0, 0, 0.0F)
    },
            // Filter
            Predicate {
                mc.currentScreen !is GuiCustomHud
            }
    )


    init {
        ClientCore.eventBus.subscribe(this)
    }


    override fun renderHudElements(mouseX: Int, mouseY: Int, partialTicks: Float) {
        enabledElements.forEach {
            run {
                it.drawElement(mouseX, mouseY, partialTicks)
            }
        }
    }
}