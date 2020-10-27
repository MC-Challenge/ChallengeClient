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

package net.challenge.client.features.modules.impl.challenge

import me.zero.alpine.event.EventPriority
import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.EventHook
import me.zero.alpine.listener.Listener
import net.challenge.client.events.AttackEntityEvent
import net.challenge.client.features.modules.Module
import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VNumber
import net.minecraft.util.Vec3


@ModuleInfo("Reach Challenge", "", ModuleCategory.CHALLENGE)
object ReachChallenge : Module() {

    @VTag("Reach", "Maximum reach for you")
    private val reach = VNumber(2.0, 0.0, 3.0)

    @EventHandler
    private val attackEntityListener: Listener<AttackEntityEvent> = Listener(
            EventHook {

                val positionEyes: Vec3 = mc.renderViewEntity.getPositionEyes(1.0f)
                val distance = mc.objectMouseOver.hitVec.distanceTo(positionEyes).toFloat()

                if (distance <= reach.value)
                    return@EventHook

                it.cancel()

            }, EventPriority.HIGHEST
    )
}