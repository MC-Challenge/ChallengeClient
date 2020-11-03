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

package net.challenge.client.features.modules.impl.hud

import me.zero.alpine.event.EventPriority
import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.EventHook
import me.zero.alpine.listener.Listener
import net.challenge.client.events.AttackEntityEvent
import net.challenge.client.features.modules.HudModule
import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.client.features.modules.impl.challenge.ReachChallenge
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.hud.customHud.element.IHudPreview
import net.minecraft.util.Vec3
import java.awt.Color
import java.text.DecimalFormat

@ModuleInfo("Reach-Display", category = ModuleCategory.HUD)
object HudReach : HudModule(), IHudPreview {

    private var lastAttack: Long = 0

    private const val hasntAttacked = "Hasn't attacked"

    /**
     * Reach from player to targetEntity
     */
    private var reach: String? = null

    private var textToRender: String = hasntAttacked

    @EventHandler
    private val attackEntityListener: Listener<AttackEntityEvent> = Listener(
            EventHook {
                if (it.isCancelled) return@EventHook

                lastAttack = System.currentTimeMillis();

                val positionEyes: Vec3 = ReachChallenge.mc.renderViewEntity.getPositionEyes(1.0f)
                reach = DecimalFormat("#.##").format(mc.objectMouseOver.hitVec.distanceTo(positionEyes)) + " blocks"

            }, EventPriority.LOWEST
    )

    override fun drawElement(mouseX: Int, mouseY: Int, partialTicks: Float) {
        if (reach != null && System.currentTimeMillis() - lastAttack > 2000L) reach = null

        textToRender = if (reach == null) hasntAttacked else reach as String

        FontHandler.mcFontRenderer.drawString(textToRender, position.getX().toInt(), position.getY().toInt(), Color.WHITE.rgb)
    }

    override fun drawPreview(mouseX: Int, mouseY: Int, partialTicks: Float) {
        FontHandler.mcFontRenderer.drawString(hasntAttacked, position.getX().toInt(), position.getY().toInt(), Color.WHITE.rgb)
    }

    override fun getElementWidth(): Int {
        return FontHandler.mcFontRenderer.getStringWidth(hasntAttacked)
    }

    override fun getElementHeight(): Int {
        return FontHandler.mcFontRenderer.getFontHeight()
    }
}