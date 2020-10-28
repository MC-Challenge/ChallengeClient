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

package net.challenge.client.features.modules

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.hud.customHud.element.IHudElement
import net.challenge.client.ui.position.IPosition
import net.challenge.client.ui.position.ScaledPosition

abstract class  HudModule : Module(), IHudElement {

    override var position: IPosition = ScaledPosition(10, 10)

    override fun onEnable() {
        ClientCore.hudRenderer.enabledElements += this
        val s = ClientCore.hudRenderer.enabledElements
        super.onEnable()
    }

    override fun onDisable() {
        ClientCore.hudRenderer.enabledElements -= this
        val s = ClientCore.hudRenderer.enabledElements
        super.onDisable()
    }
}