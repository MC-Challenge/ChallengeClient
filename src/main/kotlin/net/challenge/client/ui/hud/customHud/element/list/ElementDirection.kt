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

package net.challenge.client.ui.hud.customHud.element.list

import net.challenge.client.features.modules.HudModule
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.client.ui.hud.customHud.element.IHudElement
import net.challenge.client.ui.position.IPosition
import net.challenge.client.ui.position.ScaledPosition
import net.minecraft.client.gui.Gui

@ModuleInfo(name = "Direction")
class ElementDirection : HudModule() {

    override fun drawElement(mouseX: Int, mouseY: Int, partialTicks: Float) {
        Gui.drawRect(position.getAbsoluteX(), position.getAbsoluteY(), position.getAbsoluteX() + getElementWidth(), position.getAbsoluteY() + getElementHeight(), -1)
    }

    override fun getElementWidth(): Int {
        return 10
    }

    override fun getElementHeight(): Int {
        return 10
    }
}